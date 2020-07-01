package com.jiuyunedu.sky.cms.response;

import com.jiuyunedu.sky.cms.CmsPage;
import com.jiuyunedu.sky.model.response.ResponseResult;
import com.jiuyunedu.sky.model.response.ResultCode;
import lombok.Data;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 16:26
 * @Description This is description of class
 * @Version 1.0
 */
@Data
public class CmsPageResult extends ResponseResult {
    CmsPage cmsPage;
    public CmsPageResult(ResultCode resultCode, CmsPage cmsPage) {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}
