package com.jiuyunedu.sky.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:04
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {
    String access_token;//访问token
    String refresh_token;//刷新token
    String jwt_token;//jwt令牌
}
