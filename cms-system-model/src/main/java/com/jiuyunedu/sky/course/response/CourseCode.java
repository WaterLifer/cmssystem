package com.jiuyunedu.sky.course.response;

import com.google.common.collect.ImmutableMap;
import com.jiuyunedu.sky.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;


/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:49
 * @Description This is description of class
 * @Version 1.0
 */
@ToString
public enum CourseCode implements ResultCode {
    COURSE_DENIED_DELETE(false,31001,"删除课程失败，只允许删除本机构的课程！"),
    COURSE_PUBLISH_PERVIEWISNULL(false,31002,"还没有进行课程预览！"),
    COURSE_PUBLISH_CDETAILERROR(false,31003,"创建课程详情页面出错！"),
    COURSE_PUBLISH_COURSEIDISNULL(false,31004,"课程Id为空！"),
    COURSE_PUBLISH_VIEWERROR(false,31005,"发布课程视图出错！"),
    COURSE_PUBLISH_CREATE_INDEX_ERROR(false,31015,"创建课程发布索引失败！"),
    COURSE_MEDIS_URLISNULL(false,31106,"选择的媒资文件访问地址为空！"),
    COURSE_MEDIS_NAMEISNULL(false,31107,"选择的媒资文件名称为空！"),
    COURSE_ISNULL(false, 31108, "课程为空"),
    COURSE_PLAN_PARENT_ISNULL(false, 31109, "课程计划的上级不能为空"),
    COURSE_TEACH_PLAN_ISNULL(false, 31110, "课程计划为空"),
    COURSE_DICTIONARY_CODE_ISNULL(false, 31111, "字典代码为空"),
    COURSE_DICTIONARY_ISNULL(false, 31112, "字典数据为空"),
    COURSE_CATEGORY_ISNULL(false, 31113, "课程分类为空"),
    COURSE_MEDIA_GRADE_ERROR(false, 31115, "只允许子节点选择视频"),
    COURSE_PIC_ISNULL(false, 31114, "课程图片为空");

    //操作代码
    @ApiModelProperty(value = "操作是否成功", example = "true", required = true)
    boolean success;

    //操作代码
    @ApiModelProperty(value = "操作代码", example = "22001", required = true)
    int code;
    //提示信息
    @ApiModelProperty(value = "操作提示", example = "操作过于频繁！", required = true)
    String message;
    private CourseCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }
    private static final ImmutableMap<Integer, CourseCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, CourseCode> builder = ImmutableMap.builder();
        for (CourseCode commonCode : values()) {
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
