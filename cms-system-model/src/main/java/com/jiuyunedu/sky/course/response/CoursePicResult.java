package com.jiuyunedu.sky.course.response;

import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;

@Data
public class CoursePicResult extends ResponseResult {
    CoursePic coursePic;

    public CoursePicResult(ResultCode resultCode, CoursePic coursePic) {
        super(resultCode);
        this.coursePic = coursePic;
    }
}
