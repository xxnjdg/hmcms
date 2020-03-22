package com.xxnjdg.heimacms.serviceapi.managecourseservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.servicemodel.managecourseservice.TeachPlan;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/13 18:22
 */
@Api(tags="教学计划接口",description = "教学计划接口，提供页面的增、删、改、查")
public interface TeachPlanControllerApi {

    @ApiOperation("查询用户接口")
    ResponseResult queryTeachPlanList();

    @ApiOperation("查询课程计划树列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "主键",required=true,paramType="path",dataType="String"),
    })
    ResponseResult queryTeachPlanTreeListById(String id);

    @ApiOperation("查询课程计划接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "主键",required=true,paramType="path",dataType="String"),
    })
    ResponseResult queryTeachPlanById(String id);

    @ApiOperation("插入课程计划接口")
    ResponseResult insertTeachPlan(TeachPlan teachPlan);


    @ApiOperation("更新课程计划接口")
    ResponseResult updateTeachPlan(TeachPlan teachPlan);
}
