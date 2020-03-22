package com.xxnjdg.heimacms.cmsservice.controller;

import com.xxnjdg.heimacms.cmsservice.service.CmsTemplateService;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.aop.OperationLogRetention;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.serviceapi.cmsservice.CmsTemplateControllerApi;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@RestController
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Autowired
    private CmsTemplateService cmsTemplateService;

    @Override
    @GetMapping("/cms/template/list")
    public ResponseResult queryCmsTemplateList(){
        List<CmsTemplate> cmsTemplates = cmsTemplateService.queryCmsTemplateList();
        return new ResponseResult(CommonCode.SUCCESS,cmsTemplates);
    }

    @Override
    @PostMapping("/cms/template/")
    public ResponseResult insertCmsTemplate(MultipartFile file, String siteId, String templateName, String templateParameter) {
        cmsTemplateService.insertCmsTemplate(file, siteId, templateName,templateParameter);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @PostMapping("/test")
    @OperationLogRetention(operationModel = "test1",operationType = "test2",operationDesc = "test3")
    public String testAop(String id) throws Exception {
//        throw new CustomException(CommonCode.FAILED);
//        throw new RuntimeException("my RuntimeException");
//        throw new Exception("my Exception");
        return id;
    }


}
