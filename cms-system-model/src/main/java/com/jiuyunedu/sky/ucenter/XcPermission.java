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
@Table(name="xc_permission")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcPermission {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    @Column(name="roleId")
    private String role_id;
    @Column(name="menuId")
    private String menu_id;
    @Column(name="createTime")
    private Date create_time;


}
