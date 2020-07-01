package com.jiuyunedu.sky.ucenter;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:09
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Entity
@Table(name="xc_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcRole {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column(name="role_name")
    private String roleName;
    @Column(name="roleCode")
    private String role_code;
    private String description;
    private String status;
    @Column(name="createTime")
    private Date create_time;
    @Column(name="update_time")
    private Date updateTime;


}
