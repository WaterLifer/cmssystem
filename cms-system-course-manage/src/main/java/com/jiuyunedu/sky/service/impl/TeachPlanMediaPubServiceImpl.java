package com.jiuyunedu.sky.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jiuyunedu.sky.course.TeachplanMedia;
import com.jiuyunedu.sky.course.TeachplanMediaPub;
import com.jiuyunedu.sky.dao.TeachPlanMediaPubMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.service.TeachPlanMediaPubService;
import com.jiuyunedu.sky.service.TeachPlanMediaService;
import com.jiuyunedu.sky.utils.BeanCopyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeachPlanMediaPubServiceImpl implements TeachPlanMediaPubService {

    private final TeachPlanMediaPubMapper teachPlanMediaPubMapper;
    private final TeachPlanMediaService teachPlanMediaService;

    @Autowired
    public TeachPlanMediaPubServiceImpl(TeachPlanMediaPubMapper teachPlanMediaPubMapper,
                                        TeachPlanMediaService teachPlanMediaService) {
        this.teachPlanMediaPubMapper = teachPlanMediaPubMapper;
        this.teachPlanMediaService = teachPlanMediaService;
    }

    @Override
    public long deleteByCourseId(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        return teachPlanMediaPubMapper.delete(Wrappers.<TeachplanMediaPub>query().eq("courseid", courseId));
    }

    @Override
    public void saveAll(List<TeachplanMediaPub> teachplanMediaPubs) {
        if (teachplanMediaPubs == null || teachplanMediaPubs.size() == 0) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        teachplanMediaPubs.forEach(teachPlanMediaPubMapper::insert);
    }

    @Override
    public void saveTeachPlanMediaPub(String courseId) {
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        // 查询媒资信息
        // 根据课程id查询teachplanMedia数据
        List<TeachplanMedia> teachplanMediaList = teachPlanMediaService.findByCourseId(courseId);
        // 将课程计划媒资信息存储到索引表
        // 根据课程id删除teachplanMediaPub中的数据
        this.deleteByCourseId(courseId);
        // 将查询到的teachplanMedia数据插入到teachplanMediaPub中
        this.saveAll(teachplanMediaList.stream()
            .map(teachplanMedia -> {
                TeachplanMediaPub teachplanMediaPub = new TeachplanMediaPub();
                BeanCopyUtils.copyPropertiesIgnoreNull(teachplanMedia, teachplanMediaPub);
                return teachplanMediaPub;
            })
            .collect(Collectors.toList()));
    }
}
