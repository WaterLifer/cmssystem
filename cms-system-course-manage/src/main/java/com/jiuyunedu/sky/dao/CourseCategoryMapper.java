package com.jiuyunedu.sky.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiuyunedu.sky.course.Category;
import com.jiuyunedu.sky.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseCategoryMapper extends BaseMapper<Category> {

    List<CategoryNode> getAllCategory();

}
