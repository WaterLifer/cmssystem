package com.jiuyunedu.sky.course.response;

import com.jiuyunedu.sky.course.ext.CourseView;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseViewResult extends ResponseResult {

    CourseView courseView;

    public CourseViewResult(ResultCode resultCode, CourseView courseView) {
        super(resultCode);
        this.courseView = courseView;
    }
}
