package com.jiuyunedu.sky.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiuyunedu.sky.course.Teachplan;
import com.jiuyunedu.sky.course.ext.TeachplanNode;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.course.response.TeachPlanNodeResult;
import com.jiuyunedu.sky.course.response.TeachPlanResult;
import com.jiuyunedu.sky.dao.TeachPlanMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.service.TeachPlanService;
import com.jiuyunedu.sky.utils.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeachPlanServiceImpl implements TeachPlanService {

    private final TeachPlanMapper teachPlanMapper;

    @Autowired
    public TeachPlanServiceImpl(TeachPlanMapper teachPlanMapper) {
        this.teachPlanMapper = teachPlanMapper;
    }

    @Override
    public TeachPlanNodeResult getTeachPlanByCourseId(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        TeachplanNode teachplanNode = teachPlanMapper.getTeachPlanNodeByCourseId(courseId);
        return new TeachPlanNodeResult(CommonCode.SUCCESS, teachplanNode);
    }

    @Override
    public TeachPlanResult saveOrUpdate(Teachplan teachplan) {
        if (teachplan == null) {
            ExceptionCast.throwException(CourseCode.COURSE_TEACH_PLAN_ISNULL);
        }
        if (StringUtils.isEmpty(teachplan.getCourseid()) ||
            StringUtils.isEmpty(teachplan.getPname())) {
            ExceptionCast.throwException(CommonCode.PARAM_FORMAT_FAILD);
        }
        String id = teachplan.getId();
        if (StringUtils.isNotEmpty(id)) {
            // 更新
            Teachplan result = teachPlanMapper.selectById(id);
            // 拷贝非空数据
            BeanCopyUtils.copyPropertiesIgnoreNull(teachplan, result);
            // 保存数据
            teachPlanMapper.updateById(result);
        }
        // 添加
        // 查询父节点，我们在添加课程的时候，已经往课程计划中插入了一条数据
        Teachplan result = teachPlanMapper.selectOne(Wrappers.<Teachplan>query().eq("id", teachplan.getParentid()));
        if (result == null) {
            ExceptionCast.throwException(CourseCode.COURSE_TEACH_PLAN_ISNULL);
        }
        // 获取父节点中的grade和courseId字段
        String parentGrade = result.getGrade();
        teachplan.setGrade(String.valueOf(Integer.parseInt(parentGrade) + 1));

        String courseId = result.getCourseid();
        teachplan.setCourseid(courseId);

        // 设置发布状态: 未发布
        teachplan.setStatus("0");

        // 添加
        // 设置id
        teachplan.setId(IdUtil.simpleUUID());
        teachPlanMapper.insert(teachplan);
        return new TeachPlanResult(CommonCode.SUCCESS, teachplan);
    }

    @Override
    public TeachPlanResult delById(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        Teachplan teachplan = teachPlanMapper.selectById(id);
        if (teachplan == null) {
            ExceptionCast.throwException(CourseCode.COURSE_TEACH_PLAN_ISNULL);
        }
        teachPlanMapper.deleteById(id);
        return new TeachPlanResult(CommonCode.SUCCESS, teachplan);
    }

    @Override
    public TeachPlanResult getById(String id) {
        if (StringUtils.isEmpty(id)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        Teachplan teachplan = teachPlanMapper.selectById(id);
        if (teachplan == null) {
            ExceptionCast.throwException(CourseCode.COURSE_TEACH_PLAN_ISNULL);
        }
        return new TeachPlanResult(CommonCode.SUCCESS, teachplan);
    }
}
