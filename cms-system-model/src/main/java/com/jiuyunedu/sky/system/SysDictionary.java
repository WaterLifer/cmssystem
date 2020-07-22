package com.jiuyunedu.sky.system;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:02
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "sys_dictionary")
public class SysDictionary {

    @Id
    private String id;

    @Field("d_name")
    private String name;

    @Field("d_type")
    private String type;

    @Field("d_value")
    private List<SysDictionaryValue> value;

}
