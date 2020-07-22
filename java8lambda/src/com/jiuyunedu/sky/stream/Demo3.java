package com.jiuyunedu.sky.stream;

import java.util.stream.Stream;

public class Demo2 {

    public static void main(String[] args) {
        // 获取年龄大于30岁的所有员工
        EmployeeDao.getEmployees().stream()
                .filter(employee -> employee.getAge() > 30)
                .limit(2)
                .forEach(System.out::println);
    }
}
