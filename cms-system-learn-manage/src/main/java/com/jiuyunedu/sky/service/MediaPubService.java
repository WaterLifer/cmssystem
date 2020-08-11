package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.media.response.MediaResult;

public interface MediaPubService {

    MediaResult getMedia(String courseId, String teachplanId);
}
