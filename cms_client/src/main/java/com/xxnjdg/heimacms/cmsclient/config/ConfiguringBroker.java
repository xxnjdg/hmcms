package com.xxnjdg.heimacms.cmsclient.config;

import com.rabbitmq.client.Channel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguringBroker {

    private static Log log = LogFactory.getLog(ConfiguringBroker.class);

    /**
     * 消息被拒绝（basic.reject/basic.nack）并且requeue=false 会进入死信队列
     * 使用定时器或在加多一个死信队列，方法太多，总之定时消费因异常进入死信队列的消息，消费端要100%保证幂等
     * 以下逻辑直接丢弃了
     *
     * @return 不抛不返回
     */
    @Bean
    public RabbitListenerErrorHandler myErrorHandler() {
        return (amqpMessage, message, exception) -> {
            // 这里的异常太小，可以aop捕获大的
            log.info(exception);

            //允许ack和nack
            message.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class)
                    .basicNack(message.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class), false, false);
//            return null;
            throw exception;
        };
    }

}
