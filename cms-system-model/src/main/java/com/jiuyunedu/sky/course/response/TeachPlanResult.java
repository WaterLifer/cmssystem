package com.jiuyunedu.sky.course.response;

import com.jiuyunedu.sky.course.Teachplan;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;

@Data
public class TeachPlanResult extends ResponseResult {

    Teachplan teachplan;

    public TeachPlanResult(ResultCode resultCode, Teachplan teachplan) {
        super(resultCode);
        this.teachplan = teachplan;
    }
}
