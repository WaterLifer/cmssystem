package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.filesystem.response.UploadFileResult;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface UploadControllerApi {

    UploadFileResult uploadCoursePic(CommonsMultipartFile file, String businesskey, String filetag, String metadata);

}
