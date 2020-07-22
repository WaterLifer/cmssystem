package com.jiuyunedu.sky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
@RequestMapping("/banner")
public class BannerController {

    private final RestTemplate restTemplate;

    @Autowired
    public BannerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/get/map/{id}")
    @ResponseBody
    public Map getMapData(@PathVariable("id") String configId) {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://api.jyonline.com/page/config/get/" + configId, Map.class);
        Map map = responseEntity.getBody();
        if (map != null) {
           return map;
        }
        return null;
    }

    @GetMapping("/get/{id}")
    public String getBanner(@PathVariable("id") String configId, ModelMap modelMap) {
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://api.jyonline.com/page/config/get/" + configId, Map.class);
        Map map = responseEntity.getBody();
        if (map != null) {
            modelMap.addAllAttributes(map);
        }
        return "index_banner";
    }

}
