package com.jiuyunedu.sky.course.response;

import com.jiuyunedu.sky.course.ext.TeachplanNode;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;

@Data
public class TeachPlanNodeResult extends ResponseResult {

    TeachplanNode teachplanNode;

    public TeachPlanNodeResult(ResultCode resultCode, TeachplanNode teachplanNode) {
        super(resultCode);
        this.teachplanNode = teachplanNode;
    }
}
