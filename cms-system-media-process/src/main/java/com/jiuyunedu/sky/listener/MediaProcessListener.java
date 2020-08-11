package com.jiuyunedu.sky.listener;

import com.alibaba.fastjson.JSON;
import com.jiuyunedu.sky.media.MediaFile;
import com.jiuyunedu.sky.media.MediaFileProcess_m3u8;
import com.jiuyunedu.sky.service.MediaFileService;
import com.jiuyunedu.sky.utils.HlsVideoUtil;
import com.jiuyunedu.sky.utils.Mp4VideoUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class MediaProcessListener {

    @Value("${cms-media-process.video-path}")
    private String videoPath;

    @Value("${cms-media-process.ffmpeg-path}")
    private String ffmpegPath;

    private final MediaFileService mediaFileService;

    @Autowired
    public MediaProcessListener(MediaFileService mediaFileService) {
        this.mediaFileService = mediaFileService;
    }

    @RabbitListener(queues = "${cms.mq.queue}", containerFactory = "directRabbitListenerContainerFactory")
    public void generateM3u8(String message) {
        // 1、获取媒体id
        Map mediaInfo = JSON.parseObject(message, Map.class);
        String mediaId = mediaInfo.get("mediaId").toString();
        // 2、查询媒体信息
        MediaFile mediaFile = mediaFileService.getById(mediaId);
        if (mediaFile == null) {
            return;
        }
        // 2、判断是否已经过，如果已经处理过，则不需要再处理
        String mediaStatus = mediaFile.getProcessStatus();
        if ("303002".equals(mediaStatus) || "303004".equals(mediaStatus)) {
            return;
        }

        // 3、判断媒体格式，并做相应处理（注：这里我们暂时只考虑avi格式）
        //    1）如果为avi格式，则行转换为mp4格式
        //    2）如果为mp4格式，则直接转换为m3u8
        String mediaType = mediaFile.getFileType();
        if (mediaType == null) {
            return;
        }
        // 这里暂时只处理avi格式
        if (mediaType.equals("avi")) {
            // 如果是avi格式，则需要将avi格式先转换为mp4格式
            Mp4VideoUtil mp4VideoUtil = new Mp4VideoUtil(
                    ffmpegPath,
                    videoPath + mediaFile.getFilePath() + mediaFile.getFileName(),
                    mediaFile.getFileName(),
                    videoPath + mediaFile.getFilePath());
        
            String result = mp4VideoUtil.generateMp4();
            if (result == null || !result.equals("success")) {
                // 转换失败，写入处理日志
                // 处理失败，状态码来源于数据字典
                mediaFile.setProcessStatus("303003");
                MediaFileProcess_m3u8 mediaFileProcessM3u8 = new MediaFileProcess_m3u8();
                mediaFileProcessM3u8.setErrormsg(result);
                mediaFile.setMediaFileProcess_m3u8(mediaFileProcessM3u8);
                mediaFileService.saveOrUpdate(mediaFile);
                return;
            }
        }
        // 如果是mp4，则直接转换为m3u8
        HlsVideoUtil hlsVideoUtil = new HlsVideoUtil(
                ffmpegPath,
                videoPath + mediaFile.getFilePath() + mediaFile.getFileName(),
                mediaFile.getFileId() + ".m3u8",
                videoPath + mediaFile.getFilePath() + "hls/"
        );
        String result = hlsVideoUtil.generateM3u8();
        if (result == null || !result.equals("success")) {
            // 转换失败，写入处理日志
            // 处理失败，状态码来源于数据字典
            mediaFile.setProcessStatus("303003");
            MediaFileProcess_m3u8 mediaFileProcessM3u8 = new MediaFileProcess_m3u8();
            mediaFileProcessM3u8.setErrormsg(result);
            mediaFile.setMediaFileProcess_m3u8(mediaFileProcessM3u8);
            mediaFileService.saveOrUpdate(mediaFile);
            return;
        }

        // 4、获取m3u8列表，并更新media_file数据
        mediaFile.setProcessStatus("303002");
        List<String> fileList = hlsVideoUtil.get_ts_list();
        MediaFileProcess_m3u8 mediaFileProcessM3u8 = new MediaFileProcess_m3u8();
        mediaFileProcessM3u8.setTslist(fileList);
        mediaFile.setMediaFileProcess_m3u8(mediaFileProcessM3u8);
        // m3u8文件的url路径
        mediaFile.setFileUrl(mediaFile.getFilePath() + "hls/" + mediaFile.getFileId() + ".m3u8");
        mediaFileService.saveOrUpdate(mediaFile);
    }

}
