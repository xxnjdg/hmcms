package com.xxnjdg.heimacms.servicemodel.managecourseservice;

import com.xxnjdg.heimacms.servicemodel.validatedgroup.Insert;
import com.xxnjdg.heimacms.servicemodel.validatedgroup.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 17:41
 */
@Data
@ApiModel("教学计划实体类")
public class TeachPlan {

    @NotBlank(message = "id不能为空",groups = {Update.class})
    @Size(min = 1,max = 32,message = "id长度错误",groups = {Update.class})
    @ApiModelProperty("id")
    private String id;

    @NotBlank(message = "名字不能为空",groups = {Insert.class, Update.class})
    @Size(min = 1,max = 64,message = "名字长度错误",groups = {Insert.class, Update.class})
    @ApiModelProperty("课程名字")
    private String planName;

    @ApiModelProperty("父id")
    @Size(max = 32,message = "父id长度错误",groups = {Insert.class})
    private String parentId;

    @ApiModelProperty("层级，分为1、2、3级")
    private String grade;

    @ApiModelProperty("课程类型:1视频、2文档")
    @Pattern(regexp = "^[12]?$",message = "课程类型错误",groups = {Insert.class, Update.class})
    private String planType;

    @ApiModelProperty("章节及课程时介绍")
    @Size(max = 500,message = "描述长度错误",groups = {Insert.class, Update.class})
    private String description;

    @ApiModelProperty("时长，单位分钟")
    private Double timeLength;

    @ApiModelProperty("课程id")
    @NotBlank(message = "课程id不能为空",groups = {Insert.class})
    @Size(min = 1,max = 32,message = "课程id长度错误",groups = {Insert.class})
    private String courseId;

    @ApiModelProperty("排序字段")
    @Size(max = 32,message = "排序字段长度错误",groups = {Insert.class, Update.class})
    private String orderBy;

    @NotBlank(message = "状态不能为空",groups = {Insert.class, Update.class})
    @Pattern(regexp = "^[01]$",message = "状态错误",groups = {Insert.class, Update.class})
    @ApiModelProperty("状态：未发布、已发布")
    private String status;

    @ApiModelProperty("是否试学")
    @Size(max = 1,message = "是否试学长度错误",groups = {Insert.class, Update.class})
    private String tryLearn;
}
