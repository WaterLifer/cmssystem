package com.jiuyunedu.sky.web.controller;

import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.service.ICmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/site")
public class CmsSiteController {

    private final ICmsSiteService cmsSiteService;

    @Autowired
    public CmsSiteController(ICmsSiteService cmsSiteService) {
        this.cmsSiteService = cmsSiteService;
    }

    @GetMapping("/get/all")
    public QueryResponseResult<CmsSite> getAllCmsSite() {
        return cmsSiteService.getAllCmsSite();
    }
}
