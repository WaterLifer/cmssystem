package com.jiuyunedu.sky.advice;

import com.google.common.collect.ImmutableMap;
import com.jiuyunedu.sky.exception.CustomException;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomControllerAdvice {

    // 打印一下日志信息
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomControllerAdvice.class);

    private static ImmutableMap<Class<? extends Throwable>, ResultCode> CodeMap = new ImmutableMap.Builder<Class<? extends Throwable>, ResultCode>()
            .put(HttpMessageConversionException.class, CommonCode.PARAM_FORMAT_FAILD)
            .build();

    /**
     * @Author WaterLifer
     * @Date 2020/7/7 16:37
     * @Description 自定义异常（可预知异常）
     * @Param
     * @Return
     * @Version 1.0
     */
    @ExceptionHandler(CustomException.class)
    public ResponseResult handleCustomException(CustomException customException) {
        LOGGER.error("catch custom exception: " + customException.getMessage());
        return new ResponseResult(customException.getResultCode());
    }

    /**
     * @Author WaterLifer
     * @Date 2020/7/7 16:39
     * @Description 不可预知的异常。思路：使用ImmutableMap来存放可能存放的未知异常ImmutableMap<Class, ResultCode>
     *     然后通过异常类来获取相应的异常信息，如果没有对异常类定义相应的异常信息，则统一抛出99999异常
     * @Param
     * @Return
     * @Version 1.0
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception exception) {
        LOGGER.error("catch unknown exception: " + exception.getMessage());

        ResultCode resultCode = CodeMap.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        }
        return new ResponseResult(CommonCode.SERVER_ERROR);
    }
}
