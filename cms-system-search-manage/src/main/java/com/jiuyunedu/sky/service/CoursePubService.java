package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.bean.CoursePub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.search.CourseSearchParam;

import java.util.Map;

public interface CoursePubService {

    QueryResponseResult<CoursePub> search(String page, String size, CourseSearchParam courseSearchParam);

    Map<String, CoursePub> getCoursePubById(String id);
}
