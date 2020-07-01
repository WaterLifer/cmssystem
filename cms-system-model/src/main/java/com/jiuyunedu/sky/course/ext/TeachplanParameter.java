package com.jiuyunedu.sky.course.ext;

import com.jiuyunedu.sky.course.Teachplan;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:40
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class TeachplanParameter extends Teachplan {

    //二级分类ids
    List<String> bIds;
    //三级分类ids
    List<String> cIds;

}
