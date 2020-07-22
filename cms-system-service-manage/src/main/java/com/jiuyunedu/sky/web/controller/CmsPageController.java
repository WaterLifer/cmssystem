package com.jiuyunedu.sky.web.controller;

import com.jiuyunedu.sky.api.CmsPageControllerApi;
import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.request.QueryPageRequest;
import com.jiuyunedu.sky.cms.response.CmsPageResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.service.ICmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 
 * @Author WaterLifer
 * @Date 2020/7/2 23:46
 * @Description This is description of class
 * @Version 1.0
 */
@RestController
@RequestMapping("/cms")
public class CmsPageController implements CmsPageControllerApi {

    private final ICmsPageService cmsPageService;

    @Autowired
    public CmsPageController(ICmsPageService cmsPageService) {
        this.cmsPageService = cmsPageService;
    }

    // 接口基于Http Get请求，响应Json数据；
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult<CmsPage> findCmsPageByPage(@PathVariable("page") int page,
                                                          @PathVariable("size") int size,
                                                          QueryPageRequest queryPageRequest) {
        return cmsPageService.findCmsPageByPage(page, size, queryPageRequest);
    }

    @Override
    @PostMapping("/saveOrUpdate")
    public CmsPageResult saveOrUpdate(@RequestBody CmsPage cmsPage) {
        return cmsPageService.saveOrUpdate(cmsPage);
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsPageResult findById(@PathVariable("id") String pageId) {
        return cmsPageService.findById(pageId);
    }

    @Override
    @GetMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") String pageId) {
        return cmsPageService.deleteById(pageId);
    }

    @Override
    @GetMapping("/publish/{id}")
    public ResponseResult publishPage(@PathVariable("id") String pageId) {
        return cmsPageService.publishPage(pageId);
    }
}
