package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.dao.CmsSiteRepository;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.ICmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
