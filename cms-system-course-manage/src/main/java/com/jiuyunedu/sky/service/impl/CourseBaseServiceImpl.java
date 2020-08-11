package com.jiuyunedu.sky.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiuyunedu.sky.client.CourseClient;
import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.response.CmsPageResult;
import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.CoursePub;
import com.jiuyunedu.sky.course.response.CourseBaseResult;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import com.jiuyunedu.sky.dao.CourseBaseMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.CourseBaseService;
import com.jiuyunedu.sky.service.CoursePubService;
import com.jiuyunedu.sky.service.TeachPlanMediaPubService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBaseServiceImpl implements CourseBaseService {

    @Value("${course-preview.siteId}")
    private String siteId;
    @Value("${course-preview.templateId}")
    private String templateId;
    @Value("${course-preview.pagePhysicalPath}")
    private String pagePhysicalPath;
    @Value("${course-preview.pageWebPath}")
    private String pageWebPath;
    @Value("${course-preview.dataUrl}")
    private String dataUrl;
    @Value("${course-preview.previewUrl}")
    private String previewUrl;


    private final CourseBaseMapper courseBaseMapper;
    private final CourseClient courseClient;
    private final CoursePubService coursePubService;
    private final TeachPlanMediaPubService teachPlanMediaPubService;

    @Autowired
    public CourseBaseServiceImpl(CourseBaseMapper courseBaseMapper,
                                 CourseClient courseClient,
                                 CoursePubService coursePubService,
                                 TeachPlanMediaPubService teachPlanMediaPubService) {
        this.courseBaseMapper = courseBaseMapper;
        this.courseClient = courseClient;
        this.coursePubService = coursePubService;
        this.teachPlanMediaPubService = teachPlanMediaPubService;
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
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            ExceptionCast.throwException(CourseCode.COURSE_ISNULL);
        }
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(siteId);
        cmsPage.setTemplateId(templateId);
        cmsPage.setPageName(courseId + ".html");
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageWebPath(pageWebPath);
        cmsPage.setPagePhysicalPath(pagePhysicalPath);
        cmsPage.setDataUrl(dataUrl + courseId);
        cmsPage.setPageType("0");
        // cmsPage.setPageCreateTime(new Date());

        CmsPageResult cmsPageResult = courseClient.saveOrUpdate(cmsPage);
        if (!cmsPageResult.isSuccess()) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        return new CoursePublishResult(CommonCode.SUCCESS, previewUrl + cmsPageResult.getCmsPage().getPageId());
    }

    @Override
    public CoursePublishResult publish(String courseId) {
        // 发布课程详情页面
        if (StringUtils.isEmpty(courseId)) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_COURSEIDISNULL);
        }
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if (courseBase == null) {
            ExceptionCast.throwException(CourseCode.COURSE_ISNULL);
        }
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId(siteId);
        cmsPage.setTemplateId(templateId);
        cmsPage.setPageName(courseId + ".html");
        cmsPage.setPageAliase(courseBase.getName());
        cmsPage.setPageWebPath(pageWebPath);
        cmsPage.setPagePhysicalPath(pagePhysicalPath);
        cmsPage.setDataUrl(dataUrl + courseId);
        cmsPage.setPageType("0");
        // cmsPage.setPageCreateTime(new Date());

        CoursePublishResult coursePublishResult = courseClient.publish(cmsPage);
        if (!coursePublishResult.isSuccess()) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }

        // 更新课程状态
        courseBase.setStatus("202002");
        courseBaseMapper.updateById(courseBase);

        // 创建课程索引
        // 创建课程索引信息并向数据库中保存课程索引信息
        CoursePub coursePub = coursePubService.saveOrUpdateCoursePub(coursePubService.createCoursePub(courseId));
        if (coursePub == null) {
            ExceptionCast.throwException(CourseCode.COURSE_PUBLISH_CREATE_INDEX_ERROR);
        }

        // 保存课程计划媒资到索引表
        teachPlanMediaPubService.saveTeachPlanMediaPub(courseId);

        return coursePublishResult;
    }
}
