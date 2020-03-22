package com.xxnjdg.heimacms.cmsservice.controller;

import com.xxnjdg.heimacms.cmsservice.service.CmsPageService;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.serviceapi.cmsservice.CmsPageControllerApi;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import com.xxnjdg.heimacms.servicemodel.cmsservice.response.ResponseCmsPageList;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@RestController
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @GetMapping("/getCmsPageAll")
    public List<CmsPage> getAll(){
        return cmsPageService.getAll();
    }

    @Override
    @GetMapping("/cms/page/list/{page}/{size}")
    public ResponseResult queryCmsPageList(String siteId, String templateId, String pageAliase,
                                     @PathVariable("page") int page, @PathVariable("size") int size){

        ResponseCmsPageList responseCmsPageList = cmsPageService.queryCmsPageList(siteId, templateId, pageAliase, page, size);
        return new ResponseResult(CommonCode.SUCCESS,responseCmsPageList);
    }

    @Override
    @GetMapping("/cms/page/{id}")
    public ResponseResult queryCmsPageById(@PathVariable("id") String id){
        CmsPage cmsPage = cmsPageService.queryCmsPageById(id);
        return new ResponseResult(CommonCode.SUCCESS,cmsPage);
    }

    @Override
    @PostMapping("/cms/page")
    public ResponseResult insertCmsPage(@RequestBody CmsPage cmsPage){
        cmsPageService.insertCmsPage(cmsPage);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @PutMapping("/cms/page")
    public ResponseResult updateCmsPage(@RequestBody CmsPage cmsPage){
        cmsPageService.updateCmsPage(cmsPage);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @DeleteMapping("/cms/page/{id}")
    public ResponseResult deleteCmsPageById(@PathVariable("id") String id){
        cmsPageService.deleteCmsPageById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    @Override
    @GetMapping("/cms/page/html/{id}")
    public void queryCmsPageHtmlById(@PathVariable("id") String id, HttpServletResponse response) throws IOException, TemplateException {
        String html = cmsPageService.queryCmsPageHtmlById(id);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-type","text/html;charset=utf-8");
        outputStream.write(html.getBytes());
    }

    @Override
    @PostMapping("/cms/page/html")
    public ResponseResult saveCmsPageHtmlById(@RequestBody String id) throws IOException, TemplateException {
        cmsPageService.saveCmsPageHtmlById(id);
        return new ResponseResult(CommonCode.SUCCESS);
    }


}
