package com.jiuyunedu.sky.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:28
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "cms_config")
public class CmsConfig {

    @Id
    private String id;
    private String name;
    private List<CmsConfigModel> model;

}
