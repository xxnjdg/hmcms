package com.xxnjdg.heimacms.serviceapi.cmsservice;

import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

@Api(tags="cms模板管理接口",description = "cms模板管理接口，提供页面的增、删、改、查")
public interface CmsTemplateControllerApi {

    @ApiOperation("查询模板列表")
    ResponseResult queryCmsTemplateList();

    @ApiOperation("新增模板列表")
    ResponseResult insertCmsTemplate(MultipartFile file, String siteId, String templateName,String templateParameter);
}
