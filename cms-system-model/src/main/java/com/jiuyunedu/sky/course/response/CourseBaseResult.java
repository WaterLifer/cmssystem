package com.jiuyunedu.sky.course.response;

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
public class AddCourseResult extends ResponseResult {
    public AddCourseResult(ResultCode resultCode, String courseid) {
        super(resultCode);
        this.courseid = courseid;
    }
    private String courseid;

}
