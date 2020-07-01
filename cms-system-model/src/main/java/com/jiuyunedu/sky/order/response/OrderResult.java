package com.jiuyunedu.sky.order.response;

import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import com.jiuyunedu.sky.order.XcOrders;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:58
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class OrderResult extends ResponseResult {
    private XcOrders xcOrders;
    public OrderResult(ResultCode resultCode, XcOrders xcOrders) {
        super(resultCode);
        this.xcOrders = xcOrders;
    }


}
