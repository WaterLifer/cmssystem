package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.response.CoursePicResult;

public interface CoursePicService {

    // 查询
    CoursePicResult findById(String courseId);

    // 更新
    CoursePicResult saveOrUpdate(CoursePic coursePic);
}
