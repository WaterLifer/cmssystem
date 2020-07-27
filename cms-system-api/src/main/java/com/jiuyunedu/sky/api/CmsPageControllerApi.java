package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.request.QueryPageRequest;
import com.jiuyunedu.sky.cms.response.CmsPageResult;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.ResponseResult;

/**
 * @Author WaterLifer
 * @Date 2020/7/2 16:33
 * @Description This is description of class
 * @Version 1.0
 */
public interface CmsPageControllerApi {

    QueryResponseResult<CmsPage> findCmsPageByPage(int page, int size, QueryPageRequest queryPageRequest);

    CmsPageResult saveOrUpdate(CmsPage cmsPage);

    CmsPageResult findById(String pageId);

    ResponseResult deleteById(String pageId);

    CoursePublishResult publishPage(CmsPage cmsPage);
}
