package com.jiuyunedu.sky.portalview;

import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.CourseMarket;
import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.ext.TeachplanNode;
import com.jiuyunedu.sky.report.ReportCourse;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:01
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "view_course")
public class ViewCourse implements Serializable{

    @Id
    private String id;
    private CourseBase courseBase;
    private CourseMarket courseMarket;
    private CoursePic coursePic;
    private TeachplanNode teachplan;
    //课程统计信息
    private ReportCourse reportCourse;

}
