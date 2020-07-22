package com.jiuyunedu.sky.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.CmsTemplate;
import com.jiuyunedu.sky.cms.request.QueryPageRequest;
import com.jiuyunedu.sky.cms.response.CmsCode;
import com.jiuyunedu.sky.cms.response.CmsPageResult;
import com.jiuyunedu.sky.dao.CmsPageRepository;
import com.jiuyunedu.sky.dao.CmsTemplateRepository;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.service.ICmsPageService;
import com.jiuyunedu.sky.utils.BeanCopyUtils;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author WaterLifer
 * @Date 2020/7/2 23:42
 * @Description This is description of class
 * @Version 1.0
 */
@Service
public class CmsPageService implements ICmsPageService {

    private final CmsPageRepository cmsPageRepository;
    private final RestTemplate restTemplate;
    private final CmsTemplateRepository cmsTemplateRepository;
    private final GridFsOperations gridFsOperations;
    private final TemplateEngine templateEngine;
    private final RabbitTemplate rabbitTemplate;

    @Value("${cms.mq.exchange}")
    public String exchange;

    @Autowired
    public CmsPageService(CmsPageRepository cmsPageRepository,
                          RestTemplate restTemplate,
                          CmsTemplateRepository cmsTemplateRepository,
                          GridFsOperations gridFsOperations,
                          TemplateEngine templateEngine,
                          RabbitTemplate rabbitTemplate) {
        this.cmsPageRepository = cmsPageRepository;
        this.restTemplate = restTemplate;
        this.cmsTemplateRepository = cmsTemplateRepository;
        this.gridFsOperations = gridFsOperations;
        this.templateEngine = templateEngine;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public QueryResponseResult<CmsPage> findCmsPageByPage(int page, int size, QueryPageRequest queryPageRequest) {
        // 根据站点Id、模板Id、页面别名查询页面信息
        // 页面别名模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatcher::contains);
        // 站点ID、模板Id精确匹配
        CmsPage cmsPage = new CmsPage();
        if (!StringUtils.isEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (!StringUtils.isEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if (!StringUtils.isEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        // 使用Example中的静态方法来初始化
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        // 分页数据
        Pageable pageable = PageRequest.of(page <= 0 ? 1 : page - 1, size <= 0 ? 10 : size);

        // 分页查询CmsPage集合下的数据，此方法需要传入两个参数：Example和Pageable
        // 因此我们需要知道Example和Pageable如何初始化？
        // 通过查看Example的源码（或者帮助文档），我们可以知道Example为接口，不能直接通过new的方式来进行初始化
        // 但该类中有一个静态方法：of()，因此我们可以通过此方法进行实例化
        // 同理，Pageable也一样
        Page<CmsPage> pageInfo = this.cmsPageRepository.findAll(example, pageable);

        // 为了能够直接new创建QueryResult对象，需要在QueryResult类添加@AllArgsConstructor注解
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(pageInfo.getContent(), pageInfo.getTotalElements()));
    }

    @Override
    public CmsPageResult saveOrUpdate(CmsPage cmsPage) {
        // 校验CmsPage是否为空
        if (cmsPage == null) {
            ExceptionCast.throwException(CommonCode.SERVER_ERROR);
        }

        if (StringUtils.isNotEmpty(cmsPage.getPageId())) {
            // 由于某些属性不允许修改，因此我们需要根据pageId去查询一下，然后设置要修改的属性
            Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(cmsPage.getPageId());
            if (cmsPageOptional.isPresent()) {
                CmsPage result = cmsPageOptional.get();
                // 设置要更新的数据
                BeanCopyUtils.copyPropertiesIgnoreNull(cmsPage, result);
                // 编辑
                CmsPage savedPage = cmsPageRepository.save(result);
                return new CmsPageResult(CommonCode.SUCCESS, savedPage);
            }
        } else {
            // 校验pageName、siteId、pageWebPath的惟一性（注：cmsPage由这三个字段构成数据的惟一性，需创建索引）
            // 根据这三个字段去查询，如果能够查询到，说明已经存在，如果查询不到，说明可以添加
            CmsPage page = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),
                    cmsPage.getSiteId(),
                    cmsPage.getPageWebPath());
            // 校验页面是否存在，已经存在由抛出异常
            if (page != null) {
                ExceptionCast.throwException(CmsCode.CMS_ADDPAGE_EXISTSNAME);
            }
            cmsPage.setPageId(IdUtil.simpleUUID());
            CmsPage savedPage = cmsPageRepository.save(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, savedPage);
        }
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    @Override
    public CmsPageResult findById(String pageId) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(pageId);
        return cmsPageOptional.map(cmsPage -> new CmsPageResult(CommonCode.SUCCESS, cmsPage))
                    .orElseGet(() -> new CmsPageResult(CommonCode.FAIL, null));
    }

    @Override
    public ResponseResult deleteById(String pageId) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(pageId);
        return cmsPageOptional.map(cmsPage -> {
            cmsPageRepository.deleteById(pageId);
            return new ResponseResult(CommonCode.SUCCESS);
        }).orElseGet(() -> new ResponseResult(CommonCode.FAIL));
    }

    @Override
    public String getPageHtml(String pageId) {
        // 1、获取模型数据，需要返回map数据，方便静态化
        Map model = this.getModelByPageId(pageId);
        if (model == null) {
            // 页面模型数据为空
            ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }
        // 2、获取模板内容
        String templateContent = getTemplateByPageId(pageId);
        if (templateContent == null) {
            ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        // 3、生成静态化页面(预览功能)
        String htmlCode = generateHtml(templateContent, model);
        if (StringUtils.isEmpty(htmlCode)) {
            ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        return htmlCode;
    }

    public Map getModelByPageId(String pageId) {
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            ExceptionCast.throwException(CmsCode.CMS_PAGE_NOTEXIST);
        }
        String dataUrl = cmsPage.getDataUrl();
        // 使用RestTemplate获取模型数据
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity(dataUrl, Map.class);
        return responseEntity.getBody();
    }

    public String getTemplateByPageId(String pageId) {
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            ExceptionCast.throwException(CmsCode.CMS_PAGE_NOTEXIST);
        }
        String templateId = cmsPage.getTemplateId();
        if (StringUtils.isEmpty(templateId)) {
            ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        // 根据模板id查询模板数据
        Optional<CmsTemplate> cmsTemplateOptional = cmsTemplateRepository.findById(templateId);
        if (cmsTemplateOptional.isPresent()) {
            CmsTemplate cmsTemplate = cmsTemplateOptional.get();
            // 获取模板文件的id
            String templateFileId = cmsTemplate.getTemplateFileId();
            // 使用GridFS从数据库中查询相应的数据
            GridFSFile gridFSFile = gridFsOperations.findOne(Query.query(GridFsCriteria.where("_id").is(templateFileId)));
            if (gridFSFile == null) {
                ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
            }
            // 获取输入流
            Resource resource = gridFsOperations.getResource(gridFSFile);
            try {
                return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Author WaterLifer
     * @Date 2020/7/10 15:47
     * @Description Thymeleaf中静态化中的几个概念：
     * Context：运行上下文
     * TemplateResolver：模板解析器
     * TemplateEngine：模板引擎
     * Context：
     *      上下文： 用来保存模型数据，当模板引擎渲染时，可以从Context上下文中获取数据用于渲染。
     *      当与SpringBoot结合使用时，我们放入Model的数据就会被处理到Context，作为模板渲染的数据使用。
     * TemplateResolver：
     *      模板解析器：用来读取模板相关的配置，例如：模板存放的位置信息，模板文件名称，模板文件的类型等等。
     *      当与SpringBoot结合时，TemplateResolver已经由其创建完成，并且各种配置也都有默认值，比如模板存放位置，
     *      其默认值就是：templates。比如模板文件类型，其默认值就是html。
     * TemplateEngine：
     *      模板引擎：用来解析模板的引擎，需要使用到上下文、模板解析器。分别从两者中获取模板中需要的数据，
     *      模板文件。然后利用内置的语法规则解析，从而输出解析后的文件。
     * 模板引擎进行处理的函数：
     *      templateEngine.process(“模板名”, context, writer);
     *      模板名称   上下文：里面包含模型数据   writer：输出目的地的流
     * 在输出时，我们可以指定输出的目的地，如果目的地是Response的流，那就是网络响应。如果目的地是本地文件，那就实现静态化了。
     * 而在SpringBoot中已经自动配置了模板引擎，因此我们不需要关心这个。现在我们做静态化，就是把输出的目的地改成本地文件即可！
     * @Param 
     * @Return 
     * @Version 1.0
     */
    public String generateHtml(String template, Map<String, Object> model) {
        //上下文对象，用于存放模板所需要的数据
        Context context = new Context();
        context.setVariables(model);
        // 如果需要修改默认的前后缀，需要自己定义模板解析器
        /*
            ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
            resolver.setPrefix("templates/");//模板所在目录，相对于当前classloader的classpath。
            resolver.setSuffix(".html");//模板文件后缀
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(resolver);
            注意：这里必须是resolver.setPrefix("templates/");不能是resolver.setPrefix("/templates/");
                 不然就会出现运行时错误
         */
        // 通过engine模板引擎写入本地文件
        // 在生成文件之前，我们首先在预览，只有在预览没有问题的情况下我们才生成文件
        return templateEngine.process(template, context);
    }


    public CmsPage getById(String pageId) {
        return cmsPageRepository.findById(pageId).orElse(null);
    }

    @Override
    public ResponseResult publishPage(String pageId) {
        // 获取预览代码
        String htmlCode = this.getPageHtml(pageId);
        // 将预览代码保存到GridFS
        saveToGridFS(pageId, htmlCode);
        // 向RabbitMQ发送消息
        sendMessageToMQ(pageId);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public void saveToGridFS(String pageId, String htmlCode) {
        if (StringUtils.isEmpty(htmlCode)) {
            ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        // 根据pageId来获取HtmlFileId
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            ExceptionCast.throwException(CmsCode.CMS_PAGE_NOTEXIST);
        }
        String htmlFileId = cmsPage.getHtmlFileId();
        // 然后判断GridFS中是否存在该文件，如果存在则删除，不存在则直接添加
        if (StringUtils.isNotEmpty(htmlFileId)) {
            gridFsOperations.delete(Query.query(GridFsCriteria.where("_id").is(htmlFileId)));
        }
        ObjectId objectId = gridFsOperations.store(IOUtils.toInputStream(htmlCode, StandardCharsets.UTF_8), cmsPage.getPageName());
        // 更新CmsPage中的HtmlFileId
        cmsPage.setHtmlFileId(objectId.toString());
        cmsPageRepository.save(cmsPage);
    }

    public void sendMessageToMQ(String pageId) {
        // 根据pageId来查询站点Id，封装数据成json，发送
        CmsPage cmsPage = this.getById(pageId);
        if (cmsPage == null) {
            ExceptionCast.throwException(CmsCode.CMS_PAGE_NOTEXIST);
        }
        String siteId = cmsPage.getSiteId();

        Map<String, String> msg = new HashMap<>();
        msg.put("pageId", pageId);
        String message = JSON.toJSONString(msg);

        this.rabbitTemplate.convertAndSend(exchange, siteId, message);
    }
}
