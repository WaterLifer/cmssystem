package com.jiuyunedu.sky;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jiuyunedu.sky.dao")
public class CourseManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseManageApplication.class, args);
    }
}
