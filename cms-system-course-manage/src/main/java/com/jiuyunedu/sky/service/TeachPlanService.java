package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.course.Teachplan;
import com.jiuyunedu.sky.course.response.TeachPlanNodeResult;
import com.jiuyunedu.sky.course.response.TeachPlanResult;

public interface TeachPlanService {

    TeachPlanNodeResult getTeachPlanByCourseId(String courseId);

    TeachPlanResult saveOrUpdate(Teachplan teachplan);

    TeachPlanResult delById(String id);

    TeachPlanResult getById(String id);
}
