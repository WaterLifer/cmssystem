package com.jiuyunedu.sky.report;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:02
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "report_company")
public class ReportCompany {

    @Id
    private String id;
    private Float evaluation_score;//评价分数（平均分）
    private Float good_scale;//好评率
    private Long course_num;//课程数
    private Long student_num;//学生人数
    private Long play_num;//播放次数
    

}
