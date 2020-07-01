package com.jiuyunedu.sky.ucenter.request;

import com.jiuyunedu.sky.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:04
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
