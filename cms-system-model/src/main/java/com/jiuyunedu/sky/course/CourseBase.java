package com.jiuyunedu.sky.course;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:39
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class CourseBase implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;

    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    private String status;
    private String companyId;
    private String userId;

    // 图片路径
    @TableField(exist = false)
    private CoursePic coursePic;
}
