package com.xxnjdg.heimacms.cmsservice.timer;


import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxnjdg.heimacms.cmsservice.producer.RabbitStaticHtmlSender;
import com.xxnjdg.heimacms.cmsservice.service.CmsHtmlRetryMessageService;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Component
@JobHandler("demoJob")
public class RetrySendStaticHtmlMessageTask extends IJobHandler {

    private static Log log = LogFactory.getLog(RetrySendStaticHtmlMessageTask.class);

    private static Integer count = 0;

    @Value("${project.rabbitmq.exchange}")
    private String exchange;

    @Value("${project.rabbitmq.routingkey}")
    private String routingKey;

    @Autowired
    private CmsHtmlRetryMessageService cmsHtmlRetryMessageService;

    @Autowired
    private RabbitStaticHtmlSender rabbitStaticHtmlSender;

    /**
     * 异常会记录到 xxl-job日志
     * @param s
     * @return
     * @throws Exception
     */
    @Override
    public ReturnT<String> execute(String s) throws Exception {

        List<CmsHtmlRetryMessage> list =
                cmsHtmlRetryMessageService.queryCmsHtmlRetryMessageListByStatus(CmsHtmlRetryMessage.ORDER_SEND_RETRY);

        log.info("查询   " + count++ + list.size());

        list.forEach(message -> {
            if (message.getTryCount() >= 3) {
                //update fail message
                message.setStatus(CmsHtmlRetryMessage.ORDER_SEND_FAILURE);
                message.setUpdateTime(new Date());
                cmsHtmlRetryMessageService.updateCmsHtmlRetryMessage(message);
                log.info("执行中->message.getTryCount()");
            } else {
                // resend
                message.setTryCount(message.getTryCount() + 1);
                message.setUpdateTime(new Date());
                cmsHtmlRetryMessageService.updateCmsHtmlRetryMessage(message);
                rabbitStaticHtmlSender.sendMessage(message, exchange, routingKey);
                log.info("执行中->重发");
            }
        });

        return ReturnT.SUCCESS;
    }

}
