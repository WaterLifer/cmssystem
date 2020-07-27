package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.response.CmsCode;
import com.jiuyunedu.sky.dao.CmsPageRepository;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.service.ICmsPageService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

@Service
public class CmsPageService implements ICmsPageService {

    private final CmsPageRepository cmsPageRepository;
    private final GridFsOperations gridFsOperations;

    @Autowired
    public CmsPageService(CmsPageRepository cmsPageRepository,
                          GridFsOperations gridFsOperations) {
        this.cmsPageRepository = cmsPageRepository;
        this.gridFsOperations = gridFsOperations;
    }

    @Override
    public void downloadAndSave(String pageId) {
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById(pageId);
        CmsPage cmsPage = cmsPageOptional.orElse(null);
        if (cmsPage == null) {
            ExceptionCast.throwException(CmsCode.CMS_PAGE_NOTEXIST);
        }
        // 拼接全路径
        String htmlPath = cmsPage.getPagePhysicalPath() + cmsPage.getPageWebPath() + cmsPage.getPageName();
        // 根据CmsPage中的htmlFileId字段，从GridFS中读取内容并生成到指定目录
        GridFSFile gridFSFile = gridFsOperations.findOne(Query.query(GridFsCriteria.where("_id").is(cmsPage.getHtmlFileId())));
        if (gridFSFile == null) {
            ExceptionCast.throwException(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        GridFsResource gridFsResource = gridFsOperations.getResource(gridFSFile);
        // 分别获取输入流与输出流，并写入文件
        try (InputStream inputStream = gridFsResource.getInputStream();
             OutputStream outputStream = new FileOutputStream(new File(htmlPath))) {
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
