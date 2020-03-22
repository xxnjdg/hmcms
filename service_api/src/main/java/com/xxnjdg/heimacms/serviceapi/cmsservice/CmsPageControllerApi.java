package com.xxnjdg.heimacms.serviceapi.cmsservice;


import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Api(tags="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

    @ApiOperation("分页查询页面列表,支持站点id,模板id精确查询,页面别名模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数",required=true,paramType="path",dataType="int"),
            @ApiImplicitParam(name="siteId",value = "站点id",paramType="query",dataType="String"),
            @ApiImplicitParam(name="templateId",value = "模板id",paramType="query",dataType="String"),
            @ApiImplicitParam(name="pageAliase",value = "页面别名",paramType="query")
    })
    ResponseResult queryCmsPageList(String siteId, String templateId, String pageAliase, int page,  int size);

    @ApiOperation("根据id查询页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "id",required=true,paramType="path",dataType="int"),
    })
    ResponseResult queryCmsPageById(String id);

    @ApiOperation("新增用户接口")
    ResponseResult insertCmsPage(CmsPage cmsPage);

    @ApiOperation("修改用户接口")
    ResponseResult updateCmsPage(CmsPage cmsPage);

    @ApiOperation("根据id删除页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "id",required=true,paramType="path",dataType="int"),
    })
    ResponseResult deleteCmsPageById(String id);


    @ApiOperation("根据id获取静态页页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "id",required=true,paramType="path",dataType="int"),
    })
    void queryCmsPageHtmlById(String id, HttpServletResponse response) throws IOException, freemarker.template.TemplateException;


    @ApiOperation("根据id保存静态页页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value = "id",required=true,paramType="body",dataType="int"),
    })
    ResponseResult saveCmsPageHtmlById(String id) throws IOException, freemarker.template.TemplateException;
}
