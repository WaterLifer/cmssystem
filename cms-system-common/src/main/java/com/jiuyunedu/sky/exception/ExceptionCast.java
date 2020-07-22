package com.jiuyunedu.sky.exception;

import com.jiuyunedu.sky.model.response.ResultCode;

/**
 * @Author WaterLifer
 * @Date 2020/7/7 13:33
 * @Description 异常抛出类
 * @Version 1.0
 */
public class ExceptionCast {

    public static void throwException(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
