package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.cms.CmsConfig;
import com.jiuyunedu.sky.dao.CmsConfigRepository;
import com.jiuyunedu.sky.service.ICmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmsConfigService implements ICmsConfigService {

    private final CmsConfigRepository cmsConfigRepository;

    @Autowired
    public CmsConfigService(CmsConfigRepository cmsConfigRepository) {
        this.cmsConfigRepository = cmsConfigRepository;
    }

    @Override
    public CmsConfig getModel(String configId) {
        Optional<CmsConfig> cmsConfigOptional = cmsConfigRepository.findById(configId);
        return cmsConfigOptional.orElse(null);
    }
}
