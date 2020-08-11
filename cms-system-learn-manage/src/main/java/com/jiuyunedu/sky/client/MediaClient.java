package com.jiuyunedu.sky.client;

import com.jiuyunedu.sky.course.TeachplanMediaPub;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cms-system-search-manage")
public interface MediaClient {

    @GetMapping("/media/get/media/{teachplanId}")
    TeachplanMediaPub getMedia(@PathVariable("teachplanId") String teachplanId);
}
