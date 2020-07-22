package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.cms.CmsTemplate;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

/** 
 * @Author WaterLifer
 * @Date 2020/7/4 15:01
 * @Description This is description of class
 * @Version 1.0
 */
public interface CmsTemplateControllerApi {

    QueryResponseResult<CmsTemplate> getAllCmsTemplate();
}
