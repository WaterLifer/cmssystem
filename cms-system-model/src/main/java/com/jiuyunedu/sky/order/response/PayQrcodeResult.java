package com.jiuyunedu.sky.order.response;

import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:00
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class PayQrcodeResult extends ResponseResult {
    public PayQrcodeResult(ResultCode resultCode){
        super(resultCode);
    }
    private String codeUrl;
    private Float money;
    private String orderNumber;

}
