package com.xxnjdg.heimacms.servicemodel.managecourseservice.response;

import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 18:45
 */
@Data
@ApiModel("课程基本信息返回实体类")
public class CourseBaseAndCoursePicture extends CourseBase {
    private String picture;
}
