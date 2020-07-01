package com.jiuyunedu.sky.learning;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:55
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Entity
@Table(name="xc_learning_course")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcLearningCourse implements Serializable {
    private static final long serialVersionUID = -916357210051789799L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column(name = "course_id")
    private String courseId;
    @Column(name = "user_id")
    private String userId;
    private String valid;
    @Column(name = "start_time")
    private Date startTime;
    @Column(name = "end_time")
    private Date endTime;
    private String status;

}
