package com.xxnjdg.heimacms.cmsservice.producer;

import com.alibaba.fastjson.JSONObject;
import com.xxnjdg.heimacms.cmsservice.service.CmsHtmlRetryMessageService;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsHtmlRetryMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Component
public class RabbitStaticHtmlSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private CmsHtmlRetryMessageService cmsHtmlRetryMessageService;




    // TODO: 2020/2/25 ack 失败逻辑日志没记录 nack日志没记录
    /**
     * 回调函数: confirm确认
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {

        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            try {
                String messageId = correlationData.getId();
                CmsHtmlRetryMessage cmsHtmlRetryMessage = cmsHtmlRetryMessageService.queryCmsHtmlRetryMessageById(messageId);
                if(ack){
                    //如果confirm返回成功 则进行更新
                    cmsHtmlRetryMessage.setStatus(CmsHtmlRetryMessage.ORDER_SEND_SUCCESS);
                }else{
                    //失败则进行具体的后续操作:重试 或者补偿等手段，
                    // 我选择在定时器重发，可以记录nack日志
                    if(cmsHtmlRetryMessage.getStatus().equals(CmsHtmlRetryMessage.ORDER_SEND_RETRY)){
                        return;
                    }
                    cmsHtmlRetryMessage.setStatus(CmsHtmlRetryMessage.ORDER_SEND_RETRY);
                }
                cmsHtmlRetryMessage.setUpdateTime(new Date());
                cmsHtmlRetryMessageService.updateCmsHtmlRetryMessage(cmsHtmlRetryMessage);
            } catch (Exception e) {
//                e.printStackTrace();
                // 注意了，回调抛出的异常，spring-amqp会在自己的线程try-catch
                // 本服务的aop和全局异常全部失效
                // 只能在方法逻辑里try-catch,手动在本服务记录异常日志
            }
        }
    };

    /**
     * 发送消息方法调用: 构建自定义对象消息
     * @param cmsHtmlRetryMessage
     * @param exchange
     * @param routingKey
     */
    public void sendMessage(CmsHtmlRetryMessage cmsHtmlRetryMessage,String exchange,String routingKey){

        HashMap<String, String> map = new HashMap<>(2);
        map.put("cmsPageId",cmsHtmlRetryMessage.getMessage());
        map.put("cmsHtmlRetryMessageId",cmsHtmlRetryMessage.getMessageId());
        String sendString = JSONObject.toJSONString(map);

        Message message = MessageBuilder
                .withBody(sendString.getBytes())
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                .build();

        rabbitTemplate.setConfirmCallback(confirmCallback);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(cmsHtmlRetryMessage.getMessageId());
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
    }

}
