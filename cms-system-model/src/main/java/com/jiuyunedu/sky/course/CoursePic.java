package com.jiuyunedu.sky.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(name="course_pic")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CoursePic implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;

    @Id
    @GeneratedValue(generator = "jpa-assigned")
    private String courseid;
    private String pic;

}
