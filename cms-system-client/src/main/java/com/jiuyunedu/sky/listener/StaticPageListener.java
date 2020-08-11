package com.jiuyunedu.sky.listener;

import com.alibaba.fastjson.JSON;
import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.service.ICmsPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class StaticPageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticPageListener.class);

    private final ICmsPageService cmsPageService;

    @Autowired
    public StaticPageListener(ICmsPageService cmsPageService) {
        this.cmsPageService = cmsPageService;
    }

    @RabbitListener(queues = "${cms.mq.queue}")
    public void generateHtml(String message) {
        // 由于消息是以json的形式发送过来，因此我们需要将其转换为map形式
        Map msg = JSON.parseObject(message, Map.class);
        // 获取pageId
        String pageId = (String) msg.get("pageId");
        // 在保存之前，可以先去判断一下然后再执行后续的保存操作
        Optional<CmsPage> cmsPageOptional = cmsPageService.selectById(pageId);
        if (cmsPageOptional.isEmpty()) {
            LOGGER.error("接收到的pageId为{}，无法查到此页面", pageId);
            return;
        }
        // 将页面保存
        cmsPageService.downloadAndSave(pageId);
    }

}
