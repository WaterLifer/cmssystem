package com.jiuyunedu.sky.ucenter;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:09
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Entity
@Table(name="xc_company")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class XcCompany implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String name;
    private String logo;
    private String intro;
    private String email;
    private String mobile;
    private String linkname;
    private String identitypic;
    private String worktype;
    private String businesspic;
    private String status;


}
