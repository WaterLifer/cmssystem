package com.jiuyunedu.sky.cms.response;

import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:27
 * @Description This is description of class
 * @Version 1.0
 */
@Data
public class GenerateHtmlResult extends ResponseResult {
    String html;
    public GenerateHtmlResult(ResultCode resultCode, String html) {
        super(resultCode);
        this.html = html;
    }
}
