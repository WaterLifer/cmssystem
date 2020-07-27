package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.TeachPlanControllerApi;
import com.jiuyunedu.sky.course.Teachplan;
import com.jiuyunedu.sky.course.response.TeachPlanNodeResult;
import com.jiuyunedu.sky.course.response.TeachPlanResult;
import com.jiuyunedu.sky.service.TeachPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class TeachPlanController implements TeachPlanControllerApi {

    private final TeachPlanService teachPlanService;

    @Autowired
    public TeachPlanController(TeachPlanService teachPlanService) {
        this.teachPlanService = teachPlanService;
    }

    @Override
    @GetMapping("/get/course/{id}")
    public TeachPlanNodeResult getTeachPlanByCourseId(@PathVariable("id") String courseId) {
        return teachPlanService.getTeachPlanByCourseId(courseId);
    }

    @Override
    @PostMapping("/saveOrUpdate")
    public TeachPlanResult saveOrUpdate(Teachplan teachplan) {
        return teachPlanService.saveOrUpdate(teachplan);
    }

    @Override
    @GetMapping("/del/{id}")
    public TeachPlanResult delById(@PathVariable("id") String id) {
        return teachPlanService.delById(id);
    }

    @Override
    @GetMapping("/get/{id}")
    public TeachPlanResult getById(@PathVariable("id") String id) {
        return teachPlanService.getById(id);
    }
}
