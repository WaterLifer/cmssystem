package com.jiuyunedu.sky.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:50
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(indexName = "teachplan_media_pub")
public class TeachplanMediaPub implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    @Id
    @Field("teachplan_id")
    private String teachplanId;
    @Field("media_id")
    private String mediaId;
    @Field("media_fileoriginalname")
    private String mediaFileOriginalName;
    @Field("media_url")
    private String mediaUrl;
    @Field("courseid")
    private String courseId;
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private Date timestamp;
}
