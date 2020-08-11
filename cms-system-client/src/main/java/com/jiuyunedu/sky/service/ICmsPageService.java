package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.cms.CmsPage;

import java.util.Optional;

public interface ICmsPageService {

    void downloadAndSave(String pageId);

    Optional<CmsPage> selectById(String pageId);
}
