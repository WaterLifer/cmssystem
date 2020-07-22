package com.jiuyunedu.sky.stream;

public class Demo13 {
    public static void main(String[] args) {

        Integer sum = EmployeeDao.getEmployees()
                .stream()
                .map(Employee::getAge)
                .reduce(Integer::sum)
                .get();
        System.out.println(sum);
    }
}
