package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.CourseViewControllerApi;
import com.jiuyunedu.sky.course.ext.CourseView;
import com.jiuyunedu.sky.service.CourseViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class CourseViewController implements CourseViewControllerApi {

    private final CourseViewService courseViewService;

    @Autowired
    public CourseViewController(CourseViewService courseViewService) {
        this.courseViewService = courseViewService;
    }

    @Override
    @GetMapping("/get/{id}")
    public CourseView getCourseViewByCourseId(@PathVariable("id") String courseId) {
        return courseViewService.getCourseViewByCourseId(courseId);
    }
}
