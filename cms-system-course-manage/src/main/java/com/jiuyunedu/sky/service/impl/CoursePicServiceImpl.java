package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.course.response.CoursePicResult;
import com.jiuyunedu.sky.dao.CoursePicMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.service.CoursePicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursePicServiceImpl implements CoursePicService {

    private final CoursePicMapper coursePicMapper;

    @Autowired
    public CoursePicServiceImpl(CoursePicMapper coursePicMapper) {
        this.coursePicMapper = coursePicMapper;
    }

    @Override
    public CoursePicResult findById(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CoursePic coursePic = coursePicMapper.selectById(courseId);
        if (coursePic == null) {
            ExceptionCast.throwException(CourseCode.COURSE_PIC_ISNULL);
        }
        return new CoursePicResult(CommonCode.SUCCESS, coursePic);
    }

    @Override
    public CoursePicResult saveOrUpdate(CoursePic coursePic) {
        if (coursePic == null) {
            ExceptionCast.throwException(CourseCode.COURSE_PIC_ISNULL);
        }
        String courseId = coursePic.getCourseid();
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PIC_ISNULL);
        }
        // 更新
        CoursePic result = coursePicMapper.selectById(coursePic.getCourseid());
        if (result == null) {
            ExceptionCast.throwException(CourseCode.COURSE_PIC_ISNULL);
        }
        result.setPic(coursePic.getPic());
        coursePicMapper.updateById(result);
        return new CoursePicResult(CommonCode.SUCCESS, result);
    }
}
