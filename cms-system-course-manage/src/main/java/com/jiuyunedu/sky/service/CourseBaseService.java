package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.response.CourseBaseResult;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

public interface CourseBaseService {

    CourseBaseResult saveOrUpdate(CourseBase courseBase);

    QueryResponseResult<CourseBase> getByPage(String page, String size);

    CourseBaseResult getById(String courseId);

    CoursePublishResult preview(String courseId);

    CoursePublishResult publish(String courseId);
}
