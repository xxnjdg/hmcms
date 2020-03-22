package com.xxnjdg.heimacms.servicemodel.managecourseservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 17:50
 */
@Data
@ApiModel("课程分类实体类")
public class CourseCategory {
    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("分类标签默认和名称一样")
    private String label;

    @ApiModelProperty("父结点id")
    private String parentId;

    @ApiModelProperty("是否显示")
    private String isShow;

    @ApiModelProperty("排序字段")
    private int orderBy;

    @ApiModelProperty("是否叶子")
    private String isLeaf;
}
