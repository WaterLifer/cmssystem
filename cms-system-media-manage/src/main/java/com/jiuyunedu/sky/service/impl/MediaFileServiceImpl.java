package com.jiuyunedu.sky.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.fastjson.JSON;
import com.jiuyunedu.sky.dao.MediaFileRepository;
import com.jiuyunedu.sky.exception.ExceptionCast;
import com.jiuyunedu.sky.media.MediaFile;
import com.jiuyunedu.sky.media.request.QueryMediaFileRequest;
import com.jiuyunedu.sky.media.response.CheckChunkResult;
import com.jiuyunedu.sky.media.response.MediaCode;
import com.jiuyunedu.sky.model.response.CommonCode;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.service.MediaFileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MediaFileServiceImpl implements MediaFileService {

    private final MediaFileRepository mediaFileRepository;

    @Value("${media.store-path}")
    private String storePath;

    @Value("${media.mq.exchange}")
    private String exchange;

    @Value("${media.mq.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MediaFileServiceImpl(MediaFileRepository mediaFileRepository, RabbitTemplate rabbitTemplate) {
        this.mediaFileRepository = mediaFileRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * @Author WaterLifer
     * @Date 2020/7/28 10:47
     * @Description 判断文件是否存在
     * 文件的存放规则：
     *  · 一级目录：md5值的第一个字符
     *  · 二级目录：md5值的第二字符
     *  · 三级目录：md5值
     * 文件名：md5 + 文件扩展名
     * 思路：只有文件和数据库中都存在才认为该文件存在，否则创建文件夹，准备上传
     * @Param
     * @Return
     * @Version 1.0
     */
    @Override
    public ResponseResult checkFile(String fileMd5, String fileExt) {
        // 检查文件是否上传
        File file = new File(getFileAbsolutePath(fileMd5, fileExt));
        // 查询数据库中是否存在
        Optional<MediaFile> mediaFileOptional = mediaFileRepository.findById(fileMd5);
        if (file.exists() && mediaFileOptional.isPresent()) {
            ExceptionCast.throwException(MediaCode.UPLOAD_FILE_REGISTER_EXIST);
        }
        // 如果文件不存在，则创建文件夹，准备上传
        File fileFolder = new File(getFileFolderAbsolutePath(fileMd5));
        if (!fileFolder.exists()) {
            boolean status = fileFolder.mkdirs();
            if (!status) {
                ExceptionCast.throwException(MediaCode.UPLOAD_FILE_REGISTER_FAIL);
            }
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    // 获取文件的绝对路径
    public String getFileAbsolutePath(String fileMd5, String fileExt) {
        return storePath + fileMd5.charAt(0) + "/" + fileMd5.charAt(1) + "/" + fileMd5 + "/" + fileMd5 + "." + fileExt;
    }

    // 获取文件所在文件夹的路径
    public String getFileFolderAbsolutePath(String fileMd5) {
        return storePath + fileMd5.charAt(0) + "/" + fileMd5.charAt(1) + "/" + fileMd5 + "/";
    }

    /**
     * @Author WaterLifer
     * @Date 2020/7/29 8:47
     * @Description 检查分片是否存在
     * @Param
     * @Return
     * @Version 1.0
     */
    @Override
    public CheckChunkResult checkChunk(String fileMd5, String chunk) {
        File chunkFolderPath = new File(getChunkFolderPath(fileMd5));
        if (!chunkFolderPath.exists()) {
            boolean result = chunkFolderPath.mkdirs();
            if (!result) {
                ExceptionCast.throwException(MediaCode.UPLOAD_FILE_REGISTER_EXIST);
            }
        }
        // 获取块文件的路径
        File chunkFile = new File(getChunkFolderPath(fileMd5) + chunk);
        if (chunkFile.exists()) {
            return new CheckChunkResult(MediaCode.UPLOAD_FILE_REGISTER_EXIST, true);
        }
        return new CheckChunkResult(MediaCode.UPLOAD_FILE_REGISTER_EXIST, false);
    }

    // 获取分片的路径
    public String getChunkFolderPath(String fileMd5) {
        return this.getFileFolderAbsolutePath(fileMd5)+ "chunks/";
    }

    /**
     * @Author WaterLifer
     * @Date 2020/7/29 10:48
     * @Description 上传分片
     * @Param
     * @Return
     * @Version 1.0
     */
    @Override
    public ResponseResult uploadChunk(MultipartFile file, String fileMd5, String chunk) {
        if (file == null) {
            ExceptionCast.throwException(MediaCode.MEDIA_FILE_IS_NULL);
        }
        // 获取块文件路径
        File chunkFile = new File(getChunkFolderPath(fileMd5) + chunk);
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(chunkFile)) {
            IOUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.throwException(MediaCode.CHUNK_FILE_UPLOAD_FAIL);
        }
        return new ResponseResult(CommonCode.SUCCESS);
    }

    /**
     * @Author WaterLifer
     * @Date 2020/7/29 11:06
     * @Description 合并分块
     * 思路：
     *  1、创建合并文件
     *  2、获取块文件列表（排序）
     *  3、合并文件
     *  4、md5校验
     *  5、将文件信息保存到数据库
     *  6、删除分片文件夹
     *  7、发送消息，处理视频
     * @Param
     * @Return
     * @Version 1.0
     */
    @Override
    public ResponseResult mergeChunk(String fileMd5, String fileName, Long fileSize, String mimeType, String fileExt) {
        // 1、创建合并文件
       File target = createMergeFile(fileMd5, fileExt);
       //  2、获取块文件列表（排序）
       List<File> chunkFiles = getOrderChunkFiles(fileMd5);
       //  3、合并文件
       File mergeFile = mergeFile(target, chunkFiles);
       // 4、md5校验
        boolean result = checkFileMd5(mergeFile, fileMd5);
        if (!result) {
            ExceptionCast.throwException(MediaCode.MERGE_FILE_CHECKFAIL);
        }
        // 5、将文件信息保存到数据库，并将文件上传状态修改为上传成功
        MediaFile mediaFile = saveMediaFile(fileMd5, fileName, fileSize, mimeType, fileExt);
        // 6、删除分片文件夹
        deleteChunkFolder(fileMd5);
        // 7、发送消息，处理视频
        this.sendProcessMessage(mediaFile.getFileId());
        return new ResponseResult(CommonCode.SUCCESS);
    }

    // 1、创建合并文件
    public File createMergeFile(String fileMd5, String fileExt) {
        File target = new File(getFileAbsolutePath(fileMd5, fileExt));
        if (target.exists()) {
            boolean isDel = target.delete();
            if (!isDel) {
                ExceptionCast.throwException(MediaCode.MEDIA_FILE_DEL_FAIL);
            }
        }
        boolean newFile = false;
        try {
            newFile = target.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.throwException(MediaCode.MERGE_FILE_CREATE_FAIL);
        }
        if (!newFile) {
            ExceptionCast.throwException(MediaCode.MERGE_FILE_CREATE_FAIL);
        }
        return target;
    }

    // 2、分片文件排序
    public List<File> getOrderChunkFiles(String fileMd5) {
        File[] chunkFiles = new File(getChunkFolderPath(fileMd5)).listFiles();
        if (chunkFiles == null || chunkFiles.length == 0) {
            ExceptionCast.throwException(MediaCode.CHUNK_FILE_IS_NULL);
        }
        return Arrays.stream(chunkFiles)
                .sorted(Comparator.comparing(File::getName))
                .collect(Collectors.toList());
    }

    // 3、合并文件
    public File mergeFile(File mergeFile, List<File> chunkFiles) {
        try (RandomAccessFile writeFile = new RandomAccessFile(mergeFile, "rw")) {
            // 以字节数据的形式遍历文件并开始合并
            byte[] bytes = new byte[1024];
            chunkFiles.forEach(file -> {
                try (RandomAccessFile readFile = new RandomAccessFile(file, "r")) {
                    int len = -1;
                    while ((len = readFile.read(bytes)) != -1) {
                        writeFile.write(bytes, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mergeFile;
    }

    // 4、md5校验
    public boolean checkFileMd5(File mergeFile, String fileMd5) {
        if (mergeFile == null || fileMd5 == null) {
            return false;
        }
        try (FileInputStream fis = new FileInputStream(mergeFile)) {
            String mergeFileMd5 = DigestUtils.md5Hex(fis);

            if (mergeFileMd5.equalsIgnoreCase(fileMd5)) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 5、将文件信息保存到数据库
    public MediaFile saveMediaFile(String fileMd5, String fileName, Long fileSize, String mimeType, String fileExt) {
        MediaFile mediaFile = new MediaFile();
        mediaFile.setFileId(fileMd5);
        mediaFile.setFileName(fileMd5 + "." + fileExt);
        mediaFile.setFileOriginalName(fileName);
        mediaFile.setFilePath(fileMd5.charAt(0) + "/" + fileMd5.charAt(1) + "/" + fileMd5 + "/");
        mediaFile.setFileSize(fileSize);
        mediaFile.setUploadTime(new Date());
        mediaFile.setMimeType(mimeType);
        mediaFile.setFileType(fileExt);
        // 设置上传状态，在数据字典中查找状态码
        mediaFile.setFileStatus("301002");
        return mediaFileRepository.save(mediaFile);
    }

    // 6、删除分片文件夹
    public void deleteChunkFolder(String fileMd5) {
        File chunkFolder = new File(getChunkFolderPath(fileMd5));
        if (chunkFolder.exists() && chunkFolder.isDirectory()) {
            try {
                FileUtils.deleteDirectory(chunkFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 7、发送消息，处理视频
    private void sendProcessMessage(String mediaId) {
        if (StringUtils.isEmpty(mediaId)) {
            ExceptionCast.throwException(MediaCode.MEDIA_FILE_IS_NULL);
        }
        Optional<MediaFile> mediaFileOptional = mediaFileRepository.findById(mediaId);
        if (mediaFileOptional.isEmpty()) {
            ExceptionCast.throwException(MediaCode.MEDIA_FILE_IS_NULL);
        }
        // 统一消息发送的格式
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("mediaId", mediaId);
        String message = JSON.toJSONString(messageMap);
        // 发送消息
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }


    @Override
    public QueryResponseResult<MediaFile> queryMediaFileByPage(String page, String size, QueryMediaFileRequest mediaFileRequest) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(page) || !NumberUtil.isNumber(page)) {
            page = "0";
        }
        if (org.apache.commons.lang3.StringUtils.isEmpty(size) || !NumberUtil.isNumber(size)) {
            size = "11";
        }

        // 根据页面名称、标签和处理状态进行查询
        // 其中页面名称、标签为模糊查询、处理状态为精确查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("fileOriginalName", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("tag", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("processStatus", ExampleMatcher.GenericPropertyMatcher::exact);

        // 设置媒体文件信息
        MediaFile mediaFile = new MediaFile();
        if (StringUtils.isNotEmpty(mediaFileRequest.getFileOriginalName())) {
            mediaFile.setFileOriginalName(mediaFileRequest.getFileOriginalName());
        }
        if (StringUtils.isNotEmpty(mediaFileRequest.getTag())) {
            mediaFile.setTag(mediaFileRequest.getTag());
        }
        if (StringUtils.isNotEmpty(mediaFileRequest.getProcessStatus())) {
            mediaFile.setProcessStatus(mediaFileRequest.getProcessStatus());
        }

        // 使用Example中的静态方法来初始化
        Example<MediaFile> example = Example.of(mediaFile, exampleMatcher);
        // 分页数据
        Pageable pageable = PageRequest.of(
                Math.max(0, Integer.parseInt(page) - 1),
                Integer.parseInt(size) <= 0 ? 10 : Integer.parseInt(size));

        // 分布查询
        Page<MediaFile> mediaFiles = mediaFileRepository.findAll(example, pageable);

        // 为了能够直接new创建QueryResult对象，需要在QueryResult类添加@AllArgsConstructor注解
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(mediaFiles.getContent(), mediaFiles.getTotalElements()));
    }

    @Override
    public ResponseResult processMedia(String mediaId) {
        if (StringUtils.isEmpty(mediaId)) {
            ExceptionCast.throwException(MediaCode.MEDIA_FILE_IS_NULL);
        }
        Optional<MediaFile> mediaFileOptional = mediaFileRepository.findById(mediaId);
        if (mediaFileOptional.isEmpty()) {
            ExceptionCast.throwException(MediaCode.MEDIA_FILE_IS_NULL);
        }
        MediaFile mediaFile = mediaFileOptional.get();
        if ("303002".equals(mediaFile.getProcessStatus())
                || "303004".equals(mediaFile.getProcessStatus())) {
            return new ResponseResult(MediaCode.PROCESS_NO_NEED);
        }
        // 统一消息发送的格式
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("mediaId", mediaId);
        String message = JSON.toJSONString(messageMap);
        // 发送消息
        this.rabbitTemplate.convertAndSend(exchange, routingKey, message);

        return new ResponseResult(CommonCode.SUCCESS);
    }
}
