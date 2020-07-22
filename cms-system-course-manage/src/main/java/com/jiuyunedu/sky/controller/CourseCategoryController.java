package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.CourseCategoryControllerApi;
import com.jiuyunedu.sky.course.ext.CategoryNode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.service.CourseCategoryService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CourseCategoryController  implements CourseCategoryControllerApi {

    private final CourseCategoryService courseCategoryService;

    @Autowired
    public CourseCategoryController(CourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @Override
    @GetMapping("/all")
    public QueryResponseResult<CategoryNode> getCategory() {
        return courseCategoryService.getCategory();
    }
}
