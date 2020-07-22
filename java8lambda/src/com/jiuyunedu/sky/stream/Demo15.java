package com.jiuyunedu.sky.stream;

import java.util.List;
import java.util.stream.Collectors;

public class Demo14 {
    public static void main(String[] args) {

        List<String> names = EmployeeDao.getEmployees()
                .stream()
                .map(Employee::getName)
                .collect(Collectors.toList());

    }
}
