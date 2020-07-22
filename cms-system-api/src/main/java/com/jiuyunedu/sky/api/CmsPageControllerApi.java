package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.cms.request.QueryPageRequest;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

/**
 * @Author WaterLifer
 * @Date 2020/7/2 16:33
 * @Description This is description of class
 * @Version 1.0
 */
public interface CmsControllerApi {

    /**
     * @Author WaterLifer
     * @Date 2020/7/4 13:50
     * @Description 分页查询所有的页面信息
     * @Param
     * @Return
     * @Version 1.0
     */
    QueryResponseResult<CmsPage> findCmsPageByPage(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * @Author WaterLifer
     * @Date 2020/7/4 13:51
     * @Description 查询所有的站点信息
     * @Param
     * @Return
     * @Version 1.0
     */
    QueryResponseResult<CmsSite> findAllCmsSites();
}
