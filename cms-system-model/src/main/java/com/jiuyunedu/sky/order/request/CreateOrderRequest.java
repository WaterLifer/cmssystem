package com.jiuyunedu.sky.order.request;

import com.jiuyunedu.sky.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:57
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class CreateOrderRequest extends RequestData {

    String courseId;

}
