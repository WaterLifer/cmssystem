package com.jiuyunedu.sky.filesystem.response;

import com.jiuyunedu.sky.filesystem.FileSystem;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:50
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class UploadFileResult extends ResponseResult {
    @ApiModelProperty(value = "文件信息", example = "true", required = true)
    FileSystem fileSystem;
    public UploadFileResult(ResultCode resultCode, FileSystem fileSystem) {
        super(resultCode);
        this.fileSystem = fileSystem;
    }

}
