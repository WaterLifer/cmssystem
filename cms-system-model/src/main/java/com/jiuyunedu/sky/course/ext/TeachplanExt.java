package com.jiuyunedu.sky.course.ext;

import com.jiuyunedu.sky.course.Teachplan;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:40
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class TeachplanExt extends Teachplan {

    //媒资文件id
    private String mediaId;

    //媒资文件原始名称
    private String mediaFileOriginalName;

    //媒资文件访问地址
    private String mediaUrl;
}
