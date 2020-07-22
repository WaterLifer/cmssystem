package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.cms.CmsTemplate;
import com.jiuyunedu.sky.dao.CmsTemplateRepository;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.ICmsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmsTemplateService implements ICmsTemplateService {

    private final CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    public CmsTemplateService(CmsTemplateRepository cmsTemplateRepository) {
        this.cmsTemplateRepository = cmsTemplateRepository;
    }

    @Override
    public QueryResponseResult<CmsTemplate> findAllCmsTemplate() {
        List<CmsTemplate> cmsTemplates = cmsTemplateRepository.findAll();
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(cmsTemplates, cmsTemplates.size()));
    }
}
