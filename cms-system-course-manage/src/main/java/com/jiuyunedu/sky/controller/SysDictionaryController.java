package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.SysDictionaryControllerApi;
import com.jiuyunedu.sky.service.SysDictionaryService;
import com.jiuyunedu.sky.system.response.SysDictionaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dictionary")
public class SysDictionaryController implements SysDictionaryControllerApi {

    private final SysDictionaryService sysDictionaryService;

    @Autowired
    public SysDictionaryController(SysDictionaryService sysDictionaryService) {
        this.sysDictionaryService = sysDictionaryService;
    }

    @Override
    @GetMapping("/get/code/{code}")
    public SysDictionaryResponse findByType(@PathVariable("code") String typeCode) {
        return sysDictionaryService.findByType(typeCode);
    }
}
