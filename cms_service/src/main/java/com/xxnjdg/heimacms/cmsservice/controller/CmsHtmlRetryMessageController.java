package com.xxnjdg.heimacms.cmsservice.controller;

import com.xxnjdg.heimacms.cmsservice.service.CmsHtmlRetryMessageService;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@RestController
public class CmsHtmlRetryMessageController {

    @Autowired
    private CmsHtmlRetryMessageService cmsHtmlRetryMessageService;

    @PutMapping("/cms/html/retry/message/")
    public void updateCmsHtmlRetryMessage(String id){

        CmsHtmlRetryMessage cmsHtmlRetryMessage = cmsHtmlRetryMessageService.queryCmsHtmlRetryMessageById(id);
        cmsHtmlRetryMessage.setStatus(CmsHtmlRetryMessage.ORDER_SEND_SUCCESS);
        cmsHtmlRetryMessage.setUpdateTime(new Date());
        cmsHtmlRetryMessageService.updateCmsHtmlRetryMessage(cmsHtmlRetryMessage);
    }

    @GetMapping("/cms/html/retry/message/")
    public void queryCmsHtmlRetryMessageListByStatus(){
        List<CmsHtmlRetryMessage> list = cmsHtmlRetryMessageService.queryCmsHtmlRetryMessageListByStatus("0");
        list.forEach(System.out::println);
    }

    @PostMapping("/cms/html/retry/message/")
    public void insertCmsHtmlRetryMessage(){
        CmsHtmlRetryMessage cmsHtmlRetryMessage = new CmsHtmlRetryMessage("11111");
        cmsHtmlRetryMessageService.insertCmsHtmlRetryMessage(cmsHtmlRetryMessage);
    }

}
