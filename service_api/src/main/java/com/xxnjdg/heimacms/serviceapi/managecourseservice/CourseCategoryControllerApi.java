package com.xxnjdg.heimacms.serviceapi.managecourseservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 18:01
 */
@Api(tags="课程分类接口",description = "课程分类接口，提供页面的增、删、改、查")
public interface CourseCategoryControllerApi {

    /**
     * 查询课程分类树
     * @return
     */
    @ApiOperation("查询课程分类树")
    ResponseResult queryCourseCategoryTreeList();

}
