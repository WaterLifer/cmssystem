package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.course.ext.CategoryNode;
import com.jiuyunedu.sky.course.response.CourseCode;
import com.jiuyunedu.sky.dao.CourseCategoryMapper;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.CourseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryMapper courseCategoryMapper;

    @Autowired
    public CourseCategoryServiceImpl(CourseCategoryMapper courseCategoryMapper) {
        this.courseCategoryMapper = courseCategoryMapper;
    }

    @Override
    public QueryResponseResult<CategoryNode> getCategory() {
        List<CategoryNode> categoryNodeList = courseCategoryMapper.getAllCategory();
        if (categoryNodeList == null || categoryNodeList.size() == 0) {
            ExceptionCast.throwException(CourseCode.COURSE_CATEGORY_ISNULL);
        }
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(categoryNodeList, categoryNodeList.size()));
    }
}
