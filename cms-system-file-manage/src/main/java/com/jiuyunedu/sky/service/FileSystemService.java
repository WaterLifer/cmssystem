package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.filesystem.response.UploadFileResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface FileSystemService {

    UploadFileResult uploadCoursePic(CommonsMultipartFile file, String businesskey, String filetag, String metadata);

}
