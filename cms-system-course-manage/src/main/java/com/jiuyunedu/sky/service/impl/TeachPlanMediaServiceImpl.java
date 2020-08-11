package com.jiuyunedu.sky.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiuyunedu.sky.course.Teachplan;
import com.jiuyunedu.sky.course.TeachplanMedia;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.dao.TeachPlanMapper;
import com.jiuyunedu.sky.dao.TeachPlanMediaMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.service.TeachPlanMediaService;
import com.jiuyunedu.sky.utils.BeanCopyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachPlanMediaServiceImpl implements TeachPlanMediaService {

    private final TeachPlanMediaMapper teachPlanMediaMapper;
    private final TeachPlanMapper teachPlanMapper;

    @Autowired
    public TeachPlanMediaServiceImpl(TeachPlanMediaMapper teachPlanMediaMapper,
                                     TeachPlanMapper teachPlanMapper) {
        this.teachPlanMediaMapper = teachPlanMediaMapper;
        this.teachPlanMapper = teachPlanMapper;
    }

    @Override
    public ResponseResult saveOrUpdateMedia(TeachplanMedia teachplanMedia) {
        if (teachplanMedia == null) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        // 获取课程计划ID，然后查询课程计划，只允许叶子结点课程计划才允许选择视频
        String teachPlanId = teachplanMedia.getTeachplanId();
        // 查询课程计划
        Teachplan teachPlan = this.teachPlanMapper.selectById(teachPlanId);
        if (teachPlan == null) {
            ExceptionCast.throwException(CourseCode.COURSE_TEACH_PLAN_ISNULL);
        }
        // 获取grade属性
        String grade = teachPlan.getGrade();
        if (StringUtils.isEmpty(grade) || !"3".equals(grade)) {
            ExceptionCast.throwException(CourseCode.COURSE_MEDIA_GRADE_ERROR);
        }
        // 根据teachPlanId查询媒资文件信息
        TeachplanMedia result = teachPlanMediaMapper.selectById(teachPlanId);
        if (result == null) {
            teachPlanMediaMapper.insert(teachplanMedia);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        BeanCopyUtils.copyPropertiesIgnoreNull(teachplanMedia, result);
        teachPlanMediaMapper.updateById(result);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    public List<TeachplanMedia> findByCourseId(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        return teachPlanMediaMapper.selectList(Wrappers.<TeachplanMedia>query().eq("courseid", courseId));
    }
}
