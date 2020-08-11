package com.jiuyunedu.sky.course;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:50
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@TableName("teachplan_media")
public class TeachplanMedia implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    @TableId("teachplan_id")
    private String teachplanId;
    @TableField("media_id")
    private String mediaId;
    @TableField("media_fileoriginalname")
    private String mediaFileOriginalName;
    @TableField("media_url")
    private String mediaUrl;
    @TableField("courseid")
    private String courseId;

}
