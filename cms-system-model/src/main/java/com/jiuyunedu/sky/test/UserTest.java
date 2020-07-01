package com.jiuyunedu.sky.test;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.util.Date;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:03
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "user_test")
public class UserTest {


    @Id
    private String id;
    private String name;

    @Column(name="create_time")
    private Date createTime;
}
