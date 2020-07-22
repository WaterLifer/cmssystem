package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.CoursePicControllerApi;
import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.response.CoursePicResult;
import com.jiuyunedu.sky.service.CoursePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pic")
public class CoursePicController implements CoursePicControllerApi {

    private final CoursePicService coursePicService;

    @Autowired
    public CoursePicController(CoursePicService coursePicService) {
        this.coursePicService = coursePicService;
    }

    @Override
    @GetMapping("/get/{id}")
    public CoursePicResult findById(@PathVariable("id") String courseId) {
        return coursePicService.findById(courseId);
    }

    @Override
    @PostMapping("/saveOrUpdate")
    public CoursePicResult saveOrUpdate(CoursePic coursePic) {
        return coursePicService.saveOrUpdate(coursePic);
    }
}
