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
@Table(name="xc_user_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcUserRole {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name="user_id")
    private String userId;
    @Column(name="role_id")
    private String roleId;
    private String creator;
    @Column(name="create_time")
    private Date createTime;

}
