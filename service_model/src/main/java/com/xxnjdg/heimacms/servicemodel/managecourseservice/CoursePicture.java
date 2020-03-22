package com.xxnjdg.heimacms.servicemodel.managecourseservice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 17:51
 */
@Data
@ApiModel("课程图片信息实体类")
public class CoursePicture {
    @ApiModelProperty("课程id")
    private String courseId;

    @ApiModelProperty("图片id")
    private String picture;
}
