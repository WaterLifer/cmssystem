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
@Document(indexName = "course")
public class CoursePub implements Serializable {
    private static final long serialVersionUID = -916357110051689487L;
    @Id
    private String id;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String name;
    @Field(index = false)
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String description;
    @Field(index = false)
    private String pic;//图片
    @Field(type = FieldType.Date, format = DateFormat.date_optional_time)
    private Date timestamp;//时间戳
    private String charge;
    private String valid;
    @Field(index = false)
    private String qq;
    private Float price;
    private Float price_old;
    private String expires;
    private String teachplan;//课程计划
    @Field(name = "pub_time")
    private String pubTime;//课程发布时间
}

