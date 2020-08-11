package com.jiuyunedu.sky.controller;

import com.jiuyunedu.sky.bean.TeachplanMediaPub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;
import com.jiuyunedu.sky.model.response.QueryResult;
import com.jiuyunedu.sky.service.TeachPlanMediaPubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/media")
public class TeachPlanMediaPubController {

    private final TeachPlanMediaPubService teachPlanMediaPubService;

    @Autowired
    public TeachPlanMediaPubController(TeachPlanMediaPubService teachPlanMediaPubService) {
        this.teachPlanMediaPubService = teachPlanMediaPubService;
    }

    @GetMapping("/get/media/{teachplanId}")
    public TeachplanMediaPub getMedia(@PathVariable("teachplanId") String teachplanId) {
        // 通过ES获取课程媒资信息
        QueryResponseResult<TeachplanMediaPub> teachplanMediaPubQueryResponseResult
                = teachPlanMediaPubService.getMedia(Arrays.asList(teachplanId));
        QueryResult<TeachplanMediaPub> queryResult = teachplanMediaPubQueryResponseResult.getQueryResult();
        if (queryResult != null && queryResult.getList() != null && queryResult.getTotal() > 0) {
            return queryResult.getList().get(0);
        }
        return new TeachplanMediaPub();
    }
}
