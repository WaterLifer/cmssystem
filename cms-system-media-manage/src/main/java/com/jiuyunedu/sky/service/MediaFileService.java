package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.media.MediaFile;
import com.jiuyunedu.sky.media.request.QueryMediaFileRequest;
import com.jiuyunedu.sky.media.response.CheckChunkResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface MediaFileService {

    ResponseResult checkFile(String fileMd5, String fileExt);

    CheckChunkResult checkChunk(String fileMd5, String  chunk);

    ResponseResult uploadChunk(MultipartFile file, String fileMd5, String chunk);

    ResponseResult mergeChunk(String fileMd5, String fileName, Long fileSize, String mimeType, String fileExt);

    QueryResponseResult<MediaFile> queryMediaFileByPage(String page, String size, QueryMediaFileRequest mediaFileRequest);

    ResponseResult processMedia(String mediaId);
}
