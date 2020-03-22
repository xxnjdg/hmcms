package com.xxnjdg.heimacms.cmsservice.service;

import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;

import java.util.List;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public interface CmsHtmlRetryMessageService {

    List<CmsHtmlRetryMessage> queryCmsHtmlRetryMessageListByStatus(String status);

    CmsHtmlRetryMessage queryCmsHtmlRetryMessageById(String id);

    void insertCmsHtmlRetryMessage(CmsHtmlRetryMessage cmsHtmlRetryMessage);

    void updateCmsHtmlRetryMessage(CmsHtmlRetryMessage cmsHtmlRetryMessage);

}
