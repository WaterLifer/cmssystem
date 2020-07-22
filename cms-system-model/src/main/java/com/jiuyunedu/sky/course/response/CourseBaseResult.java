package com.jiuyunedu.sky.course.response;

import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:42
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class CourseBaseResult extends ResponseResult {

    CourseBase courseBase;

    public CourseBaseResult(ResultCode resultCode, CourseBase courseBase) {
        super(resultCode);
        this.courseBase = courseBase;
    }
}
