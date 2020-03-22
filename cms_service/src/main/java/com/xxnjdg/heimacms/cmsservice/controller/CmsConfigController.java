package com.xxnjdg.heimacms.cmsservice.controller;

import com.xxnjdg.heimacms.cmsservice.service.CmsConfigService;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.serviceapi.cmsservice.CmsConfigControllerApi;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@RestController
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    private CmsConfigService cmsConfigService;

    @Override
    @GetMapping("/cms/config/{id}")
    public ResponseResult queryCmsConfigById(@PathVariable("id") String  id) {
        CmsConfig cmsConfig = cmsConfigService.queryCmsConfigById(id);
        return new ResponseResult(CommonCode.SUCCESS,cmsConfig);
    }
}
