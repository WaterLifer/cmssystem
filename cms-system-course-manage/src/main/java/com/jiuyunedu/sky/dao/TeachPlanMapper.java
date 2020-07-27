package com.jiuyunedu.sky.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuyunedu.sky.course.Teachplan;
import com.jiuyunedu.sky.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeachPlanMapper extends BaseMapper<Teachplan> {

    TeachplanNode getTeachPlanNodeByCourseId(@Param("courseId") String courseId);
}
