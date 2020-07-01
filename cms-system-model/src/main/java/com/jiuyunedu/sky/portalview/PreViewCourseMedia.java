package com.jiuyunedu.sky.portalview;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:02
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "pre_view_course_media")
public class PreViewCourseMedia implements Serializable{

    @Id
    @Column(name="teachplan_id")
    private String teachplanId;

    @Column(name="media_id")
    private String mediaId;

    @Column(name="media_fileoriginalname")
    private String mediaFileOriginalName;

    @Column(name="media_url")
    private String mediaUrl;
    private String courseId;

}
