package com.jiuyunedu.sky.media.request;

import com.jiuyunedu.sky.model.request.RequestData;
import lombok.Data;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:56
 * @Description This is description of class
 * @Version 1.0
 */
@Data
public class QueryMediaFileRequest extends RequestData {

    private String fileOriginalName;
    private String processStatus;
    private String tag;
}
