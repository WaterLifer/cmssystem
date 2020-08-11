package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.media.response.MediaResult;
import com.jiuyunedu.sky.service.MediaPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class MediaPubController {

    private final MediaPubService mediaPubService;

    @Autowired
    public MediaPubController(MediaPubService mediaPubService) {
        this.mediaPubService = mediaPubService;
    }

    @GetMapping("/get/url/{courseId}/{planId}")
    public MediaResult getMedia(@PathVariable("courseId") String courseId, @PathVariable("planId") String teachplanId) {
        return mediaPubService.getMedia(courseId, teachplanId);
    }

}
