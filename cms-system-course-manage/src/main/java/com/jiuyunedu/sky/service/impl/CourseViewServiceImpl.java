package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.CourseMarket;
import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.ext.CourseView;
import com.jiuyunedu.sky.course.ext.TeachplanNode;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.dao.CourseBaseMapper;
import com.jiuyunedu.sky.dao.CourseMarketMapper;
import com.jiuyunedu.sky.dao.CoursePicMapper;
import com.jiuyunedu.sky.dao.TeachPlanMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.service.CourseViewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseViewServiceImpl implements CourseViewService {

    private final CourseBaseMapper courseBaseMapper;
    private final CoursePicMapper coursePicMapper;
    private final TeachPlanMapper teachPlanMapper;
    private final CourseMarketMapper courseMarketMapper;

    @Autowired
    public CourseViewServiceImpl(CourseBaseMapper courseBaseMapper, CoursePicMapper coursePicMapper,
                                 TeachPlanMapper teachPlanMapper, CourseMarketMapper courseMarketMapper) {
        this.courseBaseMapper = courseBaseMapper;
        this.coursePicMapper = coursePicMapper;
        this.teachPlanMapper = teachPlanMapper;
        this.courseMarketMapper = courseMarketMapper;
    }

    @Override
    public CourseView getCourseViewByCourseId(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CourseView courseView = new CourseView();
        // 课程基本信息
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase != null) {
            courseView.setCourseBase(courseBase);
        }
        // 课程图片
        CoursePic coursePic = coursePicMapper.selectById(courseId);
        if (coursePic != null) {
            courseView.setCoursePic(coursePic);
        }
        // 课程计划
        TeachplanNode teachplanNode = teachPlanMapper.getTeachPlanNodeByCourseId(courseId);
        if (teachplanNode != null) {
            courseView.setTeachplanNode(teachplanNode);
        }
        // 营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        if (courseMarket != null) {
            courseView.setCourseMarket(courseMarket);
        }
        return courseView;
    }
}
