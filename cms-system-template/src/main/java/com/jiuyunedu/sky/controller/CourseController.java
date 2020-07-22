package com.jiuyunedu.sky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final RestTemplate restTemplate;

    @Autowired
    public CourseController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get/map/{id}")
    @ResponseBody
    public Map getMapData(@PathVariable("id") String courseId) {
        Map map = restTemplate.getForEntity("http://api.jyonline.com/course/courseview/get/" + courseId,
                Map.class).getBody();
        if (map != null) {
            return map;
        }
        return null;
    }

    @GetMapping("/get/{id}")
    public String getCoursePage(@PathVariable("id") String courseId, ModelMap modelMap) {
        Map map = restTemplate.getForEntity("http://api.jyonline.com/course/courseview/get/" + courseId,
                Map.class).getBody();
        if (map != null) {
            modelMap.addAllAttributes(map);
        }
        return "course_main_template";
    }
}
