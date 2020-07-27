package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.CmsCourseControllerApi;
import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.response.CourseBaseResult;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.service.CourseBaseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class CourseBaseController implements CmsCourseControllerApi {

    private final CourseBaseService courseBaseService;

    public CourseBaseController(CourseBaseService courseBaseService) {
        this.courseBaseService = courseBaseService;
    }

    @Override
    @PostMapping("/saveOrUpdate")
    public CourseBaseResult saveOrUpdate(CourseBase courseBase) {
        return courseBaseService.saveOrUpdate(courseBase);
    }

    @Override
    @GetMapping("/page/{page}/{size}")
    public QueryResponseResult<CourseBase> getByPage(@PathVariable("page") String page,@PathVariable("size") String size) {
        return courseBaseService.getByPage(page, size);
    }

    @Override
    @GetMapping("/get/{id}")
    public CourseBaseResult getById(@PathVariable("id") String courseId) {
        return courseBaseService.getById(courseId);
    }

    @Override
    @GetMapping("/preview/{id}")
    public CoursePublishResult preview(@PathVariable("id") String courseId) {
        return courseBaseService.preview(courseId);
    }

    @Override
    @GetMapping("/publish/{id}")
    public CoursePublishResult publish(@PathVariable("id") String courseId) {
        return courseBaseService.publish(courseId);
    }


}
