package com.jiuyunedu.sky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CourseManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseManageApplication.class, args);
    }
}
