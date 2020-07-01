package com.jiuyunedu.sky.system;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:03
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class SysDictionaryValue {

    @Field("sd_id")
    private String sdId;

    @Field("sd_name")
    private String sdName;

    @Field("sd_status")
    private String sdStatus;

}
