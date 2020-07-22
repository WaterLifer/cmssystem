package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.ext.CategoryNode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

public interface CourseCategoryService {

    QueryResponseResult<CategoryNode> getCategory();
}
