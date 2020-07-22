package com.jiuyunedu.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jiuyunedu.sky.dao.FileSystemRepository;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.filesystem.FileSystem;
import com.jiuyunedu.sky.filesystem.response.FileSystemCode;
import com.jiuyunedu.sky.filesystem.response.UploadFileResult;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.service.FileSystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Service
public class FileSystemServiceImple implements FileSystemService {

    private final FileSystemRepository fileSystemRepository;
    private final FastFileStorageClient fastFileStorageClient;

    @Autowired
    public FileSystemServiceImple(FileSystemRepository fileSystemRepository, FastFileStorageClient fastFileStorageClient) {
        this.fileSystemRepository = fileSystemRepository;
        this.fastFileStorageClient = fastFileStorageClient;
    }

    @Override
    public UploadFileResult uploadCoursePic(CommonsMultipartFile file, String businesskey, String filetag, String metadata)  {
        if (file == null) {
            ExceptionCast.throwException(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }
        try (InputStream in = file.getInputStream();
            InputStream is = file.getInputStream()) {
            String fileName = file.getOriginalFilename();

            assert fileName != null;
            StorePath storePath = fastFileStorageClient.uploadFile(in, file.getSize(),
                    fileName.substring(fileName.indexOf(".") + 1), null);

            FileSystem fileSystem = new FileSystem();
            fileSystem.setFileId(storePath.getPath());
            fileSystem.setFilePath(storePath.getPath());
            fileSystem.setFileName(fileName);
            fileSystem.setFileSize(file.getSize());
            fileSystem.setBusinesskey(businesskey);
            fileSystem.setFiletag(filetag);
            fileSystem.setFileType(file.getContentType());

            BufferedImage bufferedImage = ImageIO.read(is);
            if (bufferedImage == null) {
                ExceptionCast.throwException(FileSystemCode.FS_UPLOADFILE_FORMAT_ERROR);
            }
            fileSystem.setFileWidth(bufferedImage.getWidth());
            fileSystem.setFileHeight(bufferedImage.getHeight());
            if (StringUtils.isNotEmpty(metadata)) {
                try {
                    Map map = JSON.parseObject(metadata, Map.class);
                    fileSystem.setMetadata(map);
                } catch (Exception exception) {
                    ExceptionCast.throwException(FileSystemCode.FS_UPLOADFILE_DATA_ERROR);
                }
            }
            fileSystemRepository.save(fileSystem);
            return new UploadFileResult(CommonCode.SUCCESS, fileSystem);
        } catch (IOException exception) {
            return new UploadFileResult(FileSystemCode.FS_UPLOADFILE_SERVERFAIL, null);
        }
    }
}
