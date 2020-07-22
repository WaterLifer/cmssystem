package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.api.UploadControllerApi;
import com.jiuyunedu.sky.filesystem.response.UploadFileResult;
import com.jiuyunedu.sky.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController implements UploadControllerApi {

    private final FileSystemService fileSystemService;

    @Autowired
    public UploadController(FileSystemService fileSystemService) {
        this.fileSystemService = fileSystemService;
    }

    @Override
    @PostMapping("/course/pic")
    public UploadFileResult uploadCoursePic(@RequestParam("file") CommonsMultipartFile file, String businesskey, String filetag, String metadata) {
        return fileSystemService.uploadCoursePic(file, businesskey, filetag, metadata);
    }
}
