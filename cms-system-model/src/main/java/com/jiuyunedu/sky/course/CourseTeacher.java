package com.jiuyunedu.sky.course;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
public class CourseTeacher implements Serializable {

    private String id;
    private String name;
    private String header;
    private String introduce;
}
