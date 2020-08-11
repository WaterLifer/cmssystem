package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.TeachPlanMediaControllerApi;
import com.jiuyunedu.sky.course.TeachplanMedia;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.service.TeachPlanMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class TeachPlanMediaController implements TeachPlanMediaControllerApi {

    private final TeachPlanMediaService teachPlanMediaService;

    @Autowired
    public TeachPlanMediaController(TeachPlanMediaService teachPlanMediaService) {
        this.teachPlanMediaService = teachPlanMediaService;
    }

    @Override
    @PostMapping("/saveOrUpdate")
    public ResponseResult saveOrUpdateMedia(TeachplanMedia teachplanMedia) {
        return teachPlanMediaService.saveOrUpdateMedia(teachplanMedia);
    }
}
