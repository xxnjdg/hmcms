package com.xxnjdg.heimacms.serviceapi.cmsservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags="cms站点管理接口",description = "cms站点管理接口，提供页面的增、删、改、查")
public interface CmsSiteControllerApi {

    @ApiOperation("查询站点列表")
    ResponseResult queryCmsSiteList();
}
