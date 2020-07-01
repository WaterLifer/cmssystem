package com.jiuyunedu.sky.ucenter.ext;

import com.jiuyunedu.sky.course.ext.CategoryNode;
import com.jiuyunedu.sky.ucenter.XcMenu;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Author WaterLifer
 * @Date 2020/7/1 17:04
 * @Description This is description of class
 * @Version 1.0
 */
@Data
@ToString
public class XcMenuExt extends XcMenu {

    List<CategoryNode> children;
}
