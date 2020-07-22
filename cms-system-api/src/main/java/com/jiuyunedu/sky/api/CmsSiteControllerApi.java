package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

/**
 * @Author WaterLifer
 * @Date 2020/7/4 13:54
 * @Description CmsSite的api接口类
 * @Version 1.0
 */
public interface CmsSiteControllerApi {

    QueryResponseResult<CmsSite> getAllCmsSite();
}
