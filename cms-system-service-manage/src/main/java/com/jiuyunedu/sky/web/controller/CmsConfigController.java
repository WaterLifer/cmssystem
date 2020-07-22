package com.jiuyunedu.sky.web.controller;

import com.jiuyunedu.sky.api.CmsConfigControllerApi;
import com.jiuyunedu.sky.cms.CmsConfig;
import com.jiuyunedu.sky.service.ICmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class CmsConfigController implements CmsConfigControllerApi {

    private final ICmsConfigService cmsConfigService;

    @Autowired
    public CmsConfigController(ICmsConfigService cmsConfigService) {
        this.cmsConfigService = cmsConfigService;
    }

    @Override
    @GetMapping("/get/{id}")
    public CmsConfig getModel(@PathVariable("id") String configId) {
        return cmsConfigService.getModel(configId);
    }
}
