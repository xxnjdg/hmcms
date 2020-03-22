package com.xxnjdg.heimacms.servicemodel.managecourseservice;

import com.xxnjdg.heimacms.servicemodel.validatedgroup.Insert;
import com.xxnjdg.heimacms.servicemodel.validatedgroup.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 17:37
 */
@Data
@ApiModel("课程基本信息实体类")
public class CourseBase {
    @ApiModelProperty("id")
    @NotBlank(message = "id不能为空",groups = {Update.class})
    private String id;

    @ApiModelProperty("课程名称")
    @NotBlank(message = "名字不能为空",groups = {Insert.class, Update.class})
    private String name;

    @ApiModelProperty("适用人群")
    private String users;

    @ApiModelProperty("课程大分类")
    @NotBlank(message = "课程分类不能为空",groups = {Insert.class, Update.class})
    private String courseLargeCategory;

    @ApiModelProperty("课程等级")
    @NotBlank(message = "课程等级不能为空",groups = {Insert.class, Update.class})
    private String grade;

    @ApiModelProperty("学习模式")
    @NotBlank(message = "学习模式不能为空",groups = {Insert.class, Update.class})
    private String learningMode;

    @ApiModelProperty("授课模式")
    private String teachingMode;

    @ApiModelProperty("课程介绍")
    private String description;

    @ApiModelProperty("课程小分类")
    @NotBlank(message = "课程分类不能为空",groups = {Insert.class, Update.class})
    private String courseSmallCategory;

    @ApiModelProperty("课程状态")
    private String status;

    @ApiModelProperty("教育机构")
    private String companyId;

    @ApiModelProperty("创建用户")
    private String userId;

}
