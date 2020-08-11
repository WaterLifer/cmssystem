package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.TeachplanMedia;
import com.jiuyunedu.sky.model.response.ResponseResult;

import java.util.List;

public interface TeachPlanMediaService {

    ResponseResult saveOrUpdateMedia(TeachplanMedia teachplanMedia);

    List<TeachplanMedia> findByCourseId(String courseId);
}
