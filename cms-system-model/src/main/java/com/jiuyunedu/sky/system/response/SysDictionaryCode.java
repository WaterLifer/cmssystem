package com.jiuyunedu.sky.system.response;

import com.jiuyunedu.sky.model.response.ResultCode;

public enum SysDictionaryCode implements ResultCode {

    SYS_DICTIONARY_CODE_ISNULL(false,250001,"类型码不能为空！"),
    SYS_DICTIONARY_ISNULL(false,250002,"无法查询到指定的字典数据！");

    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    SysDictionaryCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return this.success;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
