package com.jiuyunedu.sky.media.response;

import com.google.common.collect.ImmutableMap;
import com.jiuyunedu.sky.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:56
 * @Description This is description of class
 * @Version 1.0
 */
@ToString
public enum MediaCode implements ResultCode {
    UPLOAD_FILE_REGISTER_FAIL(false,22001,"上传文件在系统注册失败，请刷新页面重试！"),
    UPLOAD_FILE_REGISTER_EXIST(false,22002,"上传文件在系统已存在！"),
    MEDIA_FILE_DEL_FAIL(false,22003,"文件删除失败！"),
    CHUNK_FILE_EXIST_CHECK(true,22004,"分块文件在系统已存在！"),
    CHUNK_FILE_IS_NULL(true,22005,"分块文件不存在！"),
    MERGE_FILE_FAIL(false,22006,"合并文件失败，文件在系统已存在！"),
    MERGE_FILE_CREATE_FAIL(false,22007,"合并文件创建失败！"),
    MEDIA_FILE_IS_NULL(false,22008,"上传文件为空！"),
    CHUNK_FILE_UPLOAD_FAIL(false,22009,"分片上传失败！"),
    MERGE_FILE_CHECKFAIL(false,22010,"合并文件校验失败！"),
    PROCESS_NO_NEED(false,22011,"文件不需要处理！");

    //操作代码
    @ApiModelProperty(value = "媒资系统操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "媒资系统操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "媒资系统操作提示", example = "文件在系统已存在！", required = true)
    String message;
    private MediaCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, MediaCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, MediaCode> builder = ImmutableMap.builder();
        for (MediaCode commonCode : values()) {
            builder.put(commonCode.code(), commonCode);
        }
        CACHE = builder.build();
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
