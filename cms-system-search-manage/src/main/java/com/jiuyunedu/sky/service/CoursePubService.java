package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.bean.CoursePub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.search.CourseSearchParam;

public interface CoursePubService {

    QueryResponseResult<CoursePub> search(String page, String size, CourseSearchParam courseSearchParam);
}
