package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.client.MediaClient;
import com.jiuyunedu.sky.course.TeachplanMediaPub;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.media.response.MediaResult;
import com.jiuyunedu.sky.model.response.CommonCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaPubServceImpl implements MediaPubService {

    private final MediaClient mediaClient;

    @Autowired
    public MediaPubServceImpl(MediaClient mediaClient) {
        this.mediaClient = mediaClient;
    }

    @Override
    public MediaResult getMedia(String courseId, String teachplanId) {
        // 检查学生的学习权限、资费等

        // 调用搜索服务查询
        TeachplanMediaPub teachplanMediaPub = mediaClient.getMedia(teachplanId);
        if (teachplanMediaPub == null || StringUtils.isEmpty(teachplanMediaPub.getMediaUrl())) {
            ExceptionCast.throwException(CommonCode.FAIL);
        }
        return new MediaResult(CommonCode.SUCCESS, teachplanMediaPub.getMediaUrl());
    }
}
