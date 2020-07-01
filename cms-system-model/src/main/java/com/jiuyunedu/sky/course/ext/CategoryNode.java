package com.jiuyunedu.sky.course.ext;

import com.jiuyunedu.sky.course.Category;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:32
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class CategoryNode extends Category {

    List<CategoryNode> children;

}
