package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.MediaFileControllerApi;
import com.jiuyunedu.sky.media.MediaFile;
import com.jiuyunedu.sky.media.request.QueryMediaFileRequest;
import com.jiuyunedu.sky.media.response.CheckChunkResult;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.service.MediaFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
public class MediaFileController implements MediaFileControllerApi {

    private final MediaFileService mediaFileService;

    @Autowired
    public MediaFileController(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @Override
    @GetMapping("/check/file")
    public ResponseResult checkFile(String fileMd5, String fileExt) {
        return mediaFileService.checkFile(fileMd5, fileExt);
    }

    @Override
    @GetMapping("/check/chunk")
    public CheckChunkResult checkChunk(String fileMd5, String chunk) {
        return mediaFileService.checkChunk(fileMd5, chunk);
    }

    @Override
    @PostMapping("/upload/chunk")
    public ResponseResult uploadChunk(@RequestParam("file") MultipartFile file,
                                      @RequestParam("fileMd5") String fileMd5,
                                      @RequestParam("chunk") String chunk) {
        return mediaFileService.uploadChunk(file, fileMd5, chunk);
    }

    @Override
    @GetMapping("/merge/chunk")
    public ResponseResult mergeChunk(String fileMd5, String fileName, Long fileSize, String mimeType, String fileExt) {
        return mediaFileService.mergeChunk(fileMd5, fileName, fileSize, mimeType, fileExt);
    }



    @Override
    @GetMapping("/page/{page}/{size}")
    public QueryResponseResult<MediaFile> queryMediaFileByPage(
            @PathVariable("page") String page,
            @PathVariable("size") String size,
            QueryMediaFileRequest mediaFileRequest) {
        return mediaFileService.queryMediaFileByPage(page, size, mediaFileRequest);
    }

    @Override
    @GetMapping("/process/{mediaId}")
    public ResponseResult processMedia(@PathVariable("mediaId") String mediaId) {
        return mediaFileService.processMedia(mediaId);
    }


}
