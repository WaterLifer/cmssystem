package com.jiuyunedu.sky.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:10
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@AllArgsConstructor
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
