package com.jiuyunedu.sky.stream;

import java.util.Optional;

public class Demo16 {

    public static void main(String[] args) {
        // 如何创建optional实例
        // Employee employee = new Employee("张三", 18, 1);
        Employee employee = new Employee("张四", 18, 1);

        Optional<Employee> employeeOptional = Optional.ofNullable(employee);

        Employee myemployee = employeeOptional.orElse(new Employee("张三", 18, 1));

        System.out.println(myemployee.getName());

    }
}
