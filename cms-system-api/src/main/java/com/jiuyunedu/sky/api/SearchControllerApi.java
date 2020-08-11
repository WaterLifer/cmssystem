package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.course.CoursePub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.search.CourseSearchParam;

public interface SearchControllerApi {

    QueryResponseResult<CoursePub> search(String page, String size, CourseSearchParam courseSearchParam);
}
