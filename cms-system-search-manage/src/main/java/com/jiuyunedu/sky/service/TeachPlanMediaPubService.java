package com.jiuyunedu.sky.service;

import com.jiuyunedu.sky.bean.TeachplanMediaPub;
import com.jiuyunedu.sky.model.response.QueryResponseResult;

import java.util.Collection;

public interface TeachPlanMediaPubService {

    QueryResponseResult<TeachplanMediaPub> getMedia(Collection<String> teachplanIds);
}
