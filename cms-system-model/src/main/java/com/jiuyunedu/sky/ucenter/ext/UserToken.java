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
public class UserToken{
    String userId;//用户id
    String utype;//用户类型
    String companyId;//用户所属企业信息
}
