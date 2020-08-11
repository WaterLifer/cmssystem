package com.jiuyunedu.sky.api;

import com.jiuyunedu.sky.course.TeachplanMedia;
import com.jiuyunedu.sky.model.response.ResponseResult;

public interface TeachPlanMediaControllerApi {

    ResponseResult saveOrUpdateMedia(TeachplanMedia teachplanMedia);

}
