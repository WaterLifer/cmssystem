package com.jiuyunedu.sky.cms.request;

import com.jiuyunedu.sky.model.request.RequestData;
import lombok.Data;

@Data
public class QueryPageRequest extends RequestData {
    //站点ID
    private String siteId;
    //模版id
    private String templateId;
    //页面ID
    private String pageId;
    //页面名称
    private String pageName;
    //别名
    private String pageAliase;
}
