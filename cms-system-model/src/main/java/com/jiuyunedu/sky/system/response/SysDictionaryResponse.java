package com.jiuyunedu.sky.system.response;

import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import com.jiuyunedu.sky.system.SysDictionary;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SysDictionaryResponse  extends ResponseResult {

    SysDictionary sysDictionary;

    public SysDictionaryResponse(ResultCode resultCode, SysDictionary sysDictionary) {
        super(resultCode);
        this.sysDictionary = sysDictionary;
    }
}
