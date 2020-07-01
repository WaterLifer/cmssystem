package com.jiuyunedu.sky.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:10
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {

    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult<T> queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

}
