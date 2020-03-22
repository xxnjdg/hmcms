package com.xxnjdg.heimacms.serviceapi.cmsservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="cms配置管理接口",value = "1111111111")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询CMS配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "id",required=true,paramType="path"),
    })
    ResponseResult queryCmsConfigById(String id);
}
