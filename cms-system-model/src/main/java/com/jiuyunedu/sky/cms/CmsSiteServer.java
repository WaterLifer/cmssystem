package com.jiuyunedu.sky.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:31
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@Document(collection = "cms_site_server")
public class CmsSiteServer {
    /**
     * 站点id、服务器IP、端口、访问地址、服务器类型（代理、静态、动态、CDN）、资源发布地址（完整的HTTP接口）、使用类型（测试、生产）
     */
    //站点id
    private String siteId;
    //服务器ID
    @Id
    private String serverId;
    //服务器IP
    private String ip;
    //端口
    private String port;
    //访问地址
    private String webPath;
    //服务器名称（代理、静态、动态、CDN）
    private String serverName;
    //资源发布地址（完整的HTTP接口）
    private String uploadPath;
    //使用类型（测试、生产）
    private String useType;
}
