package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.cms.CmsSite;
import com.jiuyunedu.sky.cms.response.CmsSiteResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

public interface ICmsSiteService {

    QueryResponseResult<CmsSite> getAllCmsSite();

    CmsSiteResult getSiteById(String siteId);
}
