package com.xxnjdg.heimacms.cmsservice.controller;

import com.xxnjdg.heimacms.cmsservice.service.CmsSiteService;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.serviceapi.cmsservice.CmsSiteControllerApi;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@RestController
public class CmsSiteController implements CmsSiteControllerApi {

    @Autowired
    private CmsSiteService cmsSiteService;

    @Override
    @GetMapping("/cms/site/list")
    public ResponseResult queryCmsSiteList() {
        List<CmsSite> cmsSites = cmsSiteService.queryCmsSiteList();
        return new ResponseResult(CommonCode.SUCCESS,cmsSites);
    }
}
