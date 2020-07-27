package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.request.QueryPageRequest;
import com.jiuyunedu.sky.cms.response.CmsPageResult;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.ResponseResult;

public interface ICmsPageService {

    QueryResponseResult<CmsPage> findCmsPageByPage(int page, int size, QueryPageRequest queryPageRequest);

    CmsPageResult saveOrUpdate(CmsPage cmsPage);

    CmsPageResult findById(String pageId);

    ResponseResult deleteById(String pageId);

    /**
     * @Author WaterLifer
     * @Date 2020/7/10 8:16
     * @Description 实现页面静态化：模板+模板数据
     * @Param
     * @Return
     * @Version 1.0
     */
    String getPageHtml(String pageId);

    CoursePublishResult publishPage(CmsPage cmsPage);
}
