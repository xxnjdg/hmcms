package com.xxnjdg.heimacms.serviceapi.cmsservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/15 14:46
 */
@Api(tags="系统字典接口",description = "系统字典接口，提供页面的增、删、改、查")
public interface SysDictionaryControllerApi {

    /**
     * 查询系统字典列表
     * @return 返回结果
     */
    @ApiOperation("查询系统字典列表")
    ResponseResult querySysDictionaryList();


    /**
     * 根据名字查询系统字典
     * @param dName 名字
     * @return 返回结果
     */
    @ApiOperation("根据名字查询系统字典")
    ResponseResult querySysDictionaryBydName(String dName);
}
