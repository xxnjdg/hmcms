package com.xxnjdg.heimacms.servicemodel.managecourseservice.response;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 17:57
 */
@Data
@ApiModel("课程分类树状实体类")
public class CourseCategoryTree extends CourseCategory {
    @ApiModelProperty("子节点")
    private List<CourseCategoryTree> children;
}
