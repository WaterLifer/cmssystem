package com.jiuyunedu.sky.cms;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:30
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class CmsConfigModel {
    private String key;
    private String name;
    private String url;
    private Map mapValue;
    private String value;

}
