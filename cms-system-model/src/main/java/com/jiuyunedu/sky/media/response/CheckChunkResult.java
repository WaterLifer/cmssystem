package com.jiuyunedu.sky.media.response;

import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:55
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class CheckChunkResult extends ResponseResult {

    public CheckChunkResult(ResultCode resultCode, boolean fileExist) {
        super(resultCode);
        this.fileExist = fileExist;
    }
    @ApiModelProperty(value = "文件分块存在标记", example = "true", required = true)
    boolean fileExist;
}
