package com.jiuyunedu.sky.cms.response;

import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CmsSiteResult extends ResponseResult {
    CmsSite cmsSite;

    public CmsSiteResult(ResultCode resultCode, CmsSite cmsSite) {
        super(resultCode);
        this.cmsSite = cmsSite;
    }
}
