package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.CoursePub;

public interface CoursePubService {

    CoursePub createCoursePub(String courseId);

    CoursePub saveOrUpdateCoursePub(CoursePub coursePub);
}
