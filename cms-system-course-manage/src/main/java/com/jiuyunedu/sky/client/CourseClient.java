package com.jiuyunedu.sky.client;

import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.cms.response.CmsPageResult;
import com.jiuyunedu.sky.course.response.CoursePublishResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "cms-system-service-manage")
public interface CourseClient {

    @PostMapping("/cms/saveOrUpdate")
    CmsPageResult saveOrUpdate(CmsPage cmsPage);

    @PostMapping("/cms/publish")
    CoursePublishResult publish(CmsPage cmsPage);
}
