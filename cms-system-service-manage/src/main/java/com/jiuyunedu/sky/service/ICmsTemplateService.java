package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.cms.CmsTemplate;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

public interface ICmsTemplateService {

    QueryResponseResult<CmsTemplate> findAllCmsTemplate();
}
