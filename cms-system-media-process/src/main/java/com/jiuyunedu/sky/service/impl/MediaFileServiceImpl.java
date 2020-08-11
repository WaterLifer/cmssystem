package com.jiuyunedu.sky.service.impl;

import com.jiuyunedu.sky.dao.MediaFileRepository;
import com.jiuyunedu.sky.media.MediaFile;
import com.jiuyunedu.sky.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    private final MediaFileRepository mediaFileRepository;

    @Autowired
    public MediaFileServiceImpl(MediaFileRepository mediaFileRepository) {
        this.mediaFileRepository = mediaFileRepository;
    }

    @Override
    public MediaFile getById(String mediaId) {
        return mediaFileRepository.findById(mediaId).orElse(null);
    }

    @Override
    public MediaFile saveOrUpdate(MediaFile mediaFile) {
        if (mediaFile == null) {
            return null;
        }
        return mediaFileRepository.save(mediaFile);
    }
}
