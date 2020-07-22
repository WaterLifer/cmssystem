package com.jiuyunedu.sky.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuyunedu.sky.course.CourseBase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseBaseMapper extends BaseMapper<CourseBase> {

    IPage<CourseBase> selectCourseBaseByPage(Page<?> page);

}
