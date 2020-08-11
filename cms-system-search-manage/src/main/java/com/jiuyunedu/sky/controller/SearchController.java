package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.bean.CoursePub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.search.CourseSearchParam;
import com.jiuyunedu.sky.service.CoursePubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final CoursePubService coursePubService;

    @Autowired
    public SearchController(CoursePubService coursePubService) {
        this.coursePubService = coursePubService;
    }

    @GetMapping("/page")
    public QueryResponseResult<CoursePub> search(String page, String size, CourseSearchParam courseSearchParam) {
        return coursePubService.search(page, size, courseSearchParam);
    }

    @GetMapping("/get/course/{id}")
    public Map<String, CoursePub> getCoursePubById(@PathVariable("id") String id) {
        return coursePubService.getCoursePubById(id);
    }
}
