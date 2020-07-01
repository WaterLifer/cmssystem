package com.jiuyunedu.sky.ucenter.ext;

import com.jiuyunedu.sky.ucenter.XcMenu;
import com.jiuyunedu.sky.ucenter.XcUser;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:04
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class XcUserExt extends XcUser {

    //权限信息
    private List<XcMenu> permissions;

    //企业信息
    private String companyId;
}
