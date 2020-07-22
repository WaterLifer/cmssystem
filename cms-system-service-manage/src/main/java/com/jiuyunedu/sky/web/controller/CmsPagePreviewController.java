package com.jiuyunedu.sky.web.controller;

import com.jiuyunedu.sky.service.ICmsPageService;
import com.jiuyunedu.sky.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/cms/preview")
public class CmsPagePreviewController extends BaseController {

    private final ICmsPageService cmsPageService;

    @Autowired
    public CmsPagePreviewController(ICmsPageService cmsPageService) {
        this.cmsPageService = cmsPageService;
    }

    @GetMapping("/{id}")
    public void preview(@PathVariable("id") String pageId) {
        String htmlCode = cmsPageService.getPageHtml(pageId);
        if (StringUtils.isNotEmpty(htmlCode)) {
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                response.setContentType("text/html; charset=UTF-8");
                outputStream.write(htmlCode.getBytes(StandardCharsets.UTF_8));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
