package com.jiuyunedu.sky.exception;

import com.jiuyunedu.sky.model.response.ResultCode;

/**
 * @Author WaterLifere
 * @Date 2020/7/7 13:27
 * @Description 自定义异常使用RuntimeException，因为RuntimeException对代码不具有侵入性，也就是说抛出RuntimeException我们可以不需要使用try...catch去捕获
 * 其次不可预知异常通常是由于系统出现bug、或一些不要抗拒的错误（比如网络中断、服务器宕机等），异常类型为
 * RuntimeException类型（运行时异常）
 * @Version 1.0
 */
public class CustomException extends RuntimeException {

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        // 异常信息为异常代码+异常信息
        super("错误代码：" + resultCode.code() + "，错误信息：" + resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
