package com.jiuyunedu.sky.course;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:49
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Entity
@TableName("course_pic")
public class CoursePic implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;

    @TableId
    private String courseid;
    private String pic;

}
