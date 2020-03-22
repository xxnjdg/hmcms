package com.xxnjdg.heimacms.cmsservice.service;


import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public interface CmsTemplateService {
    List<CmsTemplate> queryCmsTemplateList();

    void insertCmsTemplate(MultipartFile file, String siteId, String templateName, String templateParameter);

    CmsTemplate queryCmsTemplateById(String id);
}
