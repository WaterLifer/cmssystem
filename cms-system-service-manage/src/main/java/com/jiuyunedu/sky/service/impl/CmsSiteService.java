package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.cms.response.CmsSiteResult;
import com.jiuyunedu.sky.dao.CmsSiteRepository;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.ICmsSiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmsSiteService implements ICmsSiteService {

    private final CmsSiteRepository cmsSiteRepository;

    @Autowired
    public CmsSiteService(CmsSiteRepository cmsSiteRepository) {
        this.cmsSiteRepository = cmsSiteRepository;
    }

    @Override
    public QueryResponseResult<CmsSite> getAllCmsSite() {
        List<CmsSite> cmsSites = cmsSiteRepository.findAll();
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(cmsSites, cmsSites.size()));
    }

    @Override
    public CmsSiteResult getSiteById(String siteId) {
        if (StringUtils.isEmpty(siteId)) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        Optional<CmsSite> cmsSite = cmsSiteRepository.findById(siteId);
        return new CmsSiteResult(CommonCode.SUCCESS, cmsSite.orElse(null));
    }
}
