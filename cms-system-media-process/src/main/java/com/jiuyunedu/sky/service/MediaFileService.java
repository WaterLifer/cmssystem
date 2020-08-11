package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.media.MediaFile;

public interface MediaFileService {

    MediaFile getById(String mediaId);

    MediaFile saveOrUpdate(MediaFile mediaFile);
}
