package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.course.ext.CategoryNode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

public interface CourseCategoryControllerApi {

    QueryResponseResult<CategoryNode> getCategory();
}
