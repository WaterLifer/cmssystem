package com.jiuyunedu.sky.course.ext;

import com.jiuyunedu.sky.course.CourseBase;
import com.jiuyunedu.sky.course.CourseMarket;
import com.jiuyunedu.sky.course.CoursePic;
import com.jiuyunedu.sky.course.CourseTeacher;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class CourseView implements java.io.Serializable {

    CourseBase courseBase;
    CoursePic coursePic;
    CourseMarket courseMarket;
    TeachplanNode teachplanNode;
    CourseTeacher courseTeacher;

}
