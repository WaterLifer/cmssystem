package com.jiuyunedu.sky.stream;

import java.util.stream.Stream;

public class Demo1 {

    public static void main(String[] args) {
        // 获取年龄大于30岁的所有员工
        Stream<Employee> employeeStream = EmployeeDao.getEmployees().stream().filter(employee -> employee.getAge() > 30);

        employeeStream.forEach(System.out::println);
    }
}
