package com.jiuyunedu.sky.course;

import lombok.Data;
import lombok.ToString;

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
public class CoursePub implements Serializable {
    private static final long serialVersionUID = -916357110051689487L;

    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String pic;//图片
    private Date timestamp;//时间戳
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private String expires;
    private String teachplan;//课程计划
    private String pubTime;//课程发布时间
}
