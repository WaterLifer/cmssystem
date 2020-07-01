package com.jiuyunedu.sky.course.request;

import com.jiuyunedu.sky.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:41
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class CourseListRequest extends RequestData {
    //公司Id
    private String companyId;
}
