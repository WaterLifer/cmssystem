package com.jiuyunedu.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.CourseMarket;
import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.CoursePub;
import com.jiuyunedu.sky.course.ext.TeachplanNode;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.dao.*;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.service.CoursePubService;
import com.jiuyunedu.sky.utils.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class CoursePubServiceImpl implements CoursePubService {

    private final CoursePubMapper coursePubMapper;
    private final CourseBaseMapper courseBaseMapper;
    private final CoursePicMapper coursePicMapper;
    private final TeachPlanMapper teachPlanMapper;
    private final CourseMarketMapper courseMarketMapper;

    @Autowired
    public CoursePubServiceImpl(CoursePubMapper coursePubMapper,
                                CourseBaseMapper courseBaseMapper,
                                CoursePicMapper coursePicMapper,
                                TeachPlanMapper teachPlanMapper,
                                CourseMarketMapper courseMarketMapper) {
        this.coursePubMapper = coursePubMapper;
        this.courseBaseMapper = courseBaseMapper;
        this.coursePicMapper = coursePicMapper;
        this.teachPlanMapper = teachPlanMapper;
        this.courseMarketMapper = courseMarketMapper;
    }

    @Override
    public CoursePub createCoursePub(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CoursePub coursePub = new CoursePub();
        coursePub.setId(courseId);

        // 课程基本信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase != null) {
            BeanCopyUtils.copyPropertiesIgnoreNull(courseBase, coursePub);
        }
        // 课程图片
        CoursePic coursePic = coursePicMapper.selectById(courseId);
        if (coursePic != null) {
            BeanCopyUtils.copyPropertiesIgnoreNull(coursePic, coursePub);
        }
        // 课程计划
        TeachplanNode teachplanNode = teachPlanMapper.getTeachPlanNodeByCourseId(courseId);
        if (teachplanNode != null) {
            coursePub.setTeachplan(JSON.toJSONString(teachplanNode));
        }
        // 营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseMarket != null) {
            BeanCopyUtils.copyPropertiesIgnoreNull(courseMarket, coursePub);
        }
        return coursePub;
    }

    @Override
    public CoursePub saveOrUpdateCoursePub(CoursePub coursePub) {
        if (coursePub == null || StringUtils.isEmpty(coursePub.getId())) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CoursePub coursePubResult = coursePubMapper.selectById(coursePub.getId());
        if (coursePubResult == null) {
            coursePubResult = new CoursePub();
            BeanCopyUtils.copyPropertiesIgnoreNull(coursePub, coursePubResult);
            // 设置更新时间
            coursePub.setTimestamp(new Date());
            // 设置发布时间
            String time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
            coursePub.setPubTime(time);
            coursePubMapper.insert(coursePubResult);
            return coursePubResult;
        }
        BeanCopyUtils.copyPropertiesIgnoreNull(coursePub, coursePubResult);
        // 设置更新时间
        coursePub.setTimestamp(new Date());
        // 设置发布时间
        coursePub.setPubTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        coursePubMapper.updateById(coursePubResult);
        return coursePubResult;
    }
}
