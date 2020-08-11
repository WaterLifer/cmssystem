package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.TeachplanMediaPub;

import java.util.List;

public interface TeachPlanMediaPubService  {

    long deleteByCourseId(String courseId);

    void saveAll(List<TeachplanMediaPub> teachplanMediaPubs);

    void saveTeachPlanMediaPub(String courseId);
}
