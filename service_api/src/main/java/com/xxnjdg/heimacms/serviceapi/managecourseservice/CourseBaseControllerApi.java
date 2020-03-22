package com.xxnjdg.heimacms.serviceapi.managecourseservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.CourseBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/14 17:23
 */
@Api(tags="课程基本信息接口",description = "课程基本信息接口，提供页面的增、删、改、查")
public interface CourseBaseControllerApi {

    @ApiOperation("分页查询课程基本信息和图片")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int")
    })
    ResponseResult queryCourseBaseAndCoursePictureList(int page, int size);

    @ApiOperation("根据id查询课程基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "主键",required=true,paramType="path",dataType="String"),
    })
    ResponseResult queryCourseBaseById(String id);

    @ApiOperation("新增课程接口")
    ResponseResult insertCourseBase(CourseBase courseBase);

    @ApiOperation("更新课程接口")
    ResponseResult updateCourseBase(CourseBase courseBase);

}
