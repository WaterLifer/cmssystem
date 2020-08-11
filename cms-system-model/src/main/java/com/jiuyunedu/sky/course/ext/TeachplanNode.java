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
public class TeachplanNode extends Teachplan {

    List<TeachplanNode> children;

    // 媒资信息
    private String mediaId;
    private String mediaFileOriginalName;
}
