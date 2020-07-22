package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.response.CoursePicResult;

public interface CoursePicControllerApi {

    // 查询
    CoursePicResult findById(String courseId);

    // 更新
    CoursePicResult saveOrUpdate(CoursePic coursePic);

}
