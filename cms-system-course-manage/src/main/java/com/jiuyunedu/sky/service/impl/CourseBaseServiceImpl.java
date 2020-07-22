package com.jiuyunedu.sky.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.response.CourseBaseResult;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import com.jiuyunedu.sky.dao.CourseBaseMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.CourseBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBaseServiceImpl implements CourseBaseService {

    private final CourseBaseMapper courseBaseMapper;

    @Autowired
    public CourseBaseServiceImpl(CourseBaseMapper courseBaseMapper) {
        this.courseBaseMapper = courseBaseMapper;
    }

    @Override
    public CourseBaseResult saveOrUpdate(CourseBase courseBase) {
        return null;
    }

    @Override
    public QueryResponseResult<CourseBase> getByPage(String page, String size) {
        if (StringUtils.isEmpty(page) || !NumberUtil.isNumber(page)) {
            page = "0";
        }
        if (StringUtils.isEmpty(size) || !NumberUtil.isNumber(size)) {
            size = "11";
        }
        // 分页查询CourseBase
        IPage<CourseBase> courseBaseIPage = courseBaseMapper.selectCourseBaseByPage(
                new Page<CourseBase>(Integer.parseInt(page), Integer.parseInt(size))
        );
        // 拿到结果
        List<CourseBase> courseBaseList = courseBaseIPage.getRecords();
        if (courseBaseList == null || courseBaseList.size() == 0) {
           ExceptionCast.throwException(CourseCode.COURSE_ISNULL);
        }
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(courseBaseList, courseBaseIPage.getTotal()));
    }

    @Override
    public CourseBaseResult getById(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            ExceptionCast.throwException(CourseCode.COURSE_ISNULL);
        }
        return new CourseBaseResult(CommonCode.SUCCESS, courseBase);
    }

    @Override
    public CoursePublishResult preview(String courseId) {
        return null;
    }
}
