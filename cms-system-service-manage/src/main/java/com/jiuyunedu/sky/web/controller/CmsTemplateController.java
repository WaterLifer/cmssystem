package com.jiuyunedu.sky.web.controller;

import com.jiuyunedu.sky.cms.CmsTemplate;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.service.ICmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
public class CmsTemplateController {

    private final ICmsTemplateService cmsTemplateService;

    @Autowired
    public CmsTemplateController(ICmsTemplateService cmsTemplateService) {
        this.cmsTemplateService = cmsTemplateService;
    }

    @GetMapping("/get/all")
    public QueryResponseResult<CmsTemplate> getAllCmsTemplate() {
        return cmsTemplateService.findAllCmsTemplate();
    }
}
