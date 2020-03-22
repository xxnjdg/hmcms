package com.xxnjdg.heimacms.cmsservice.service;

import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import com.xxnjdg.heimacms.servicemodel.cmsservice.response.ResponseCmsPageList;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public interface CmsPageService {
    List<CmsPage> getAll();

    ResponseCmsPageList queryCmsPageList(String siteId, String templateId, String pageAliase, int page, int size);

    void insertCmsPage(CmsPage cmsPage);

    CmsPage queryCmsPageById(String id);

    void updateCmsPage(CmsPage cmsPage);

    void deleteCmsPageById(String id);

    String queryCmsPageHtmlById(String id) throws IOException, TemplateException;

    void saveCmsPageHtmlById(String id) throws IOException, TemplateException;
}
