package com.xxnjdg.heimacms.cmsclient.listener;


import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.rabbitmq.client.Channel;
import com.xxnjdg.heimacms.common.exception.CustomException;
import com.xxnjdg.heimacms.common.model.response.CommonCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import com.xxnjdg.heimacms.servicemodel.cmsservice.CmsPage;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.commands.JedisCommands;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class RabbitmqListener {

    private static Logger logger = LoggerFactory.getLogger(RabbitmqListener.class);

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 需求
     * 1、前端请求cms执行页面发布。
     * 2、cms执行静态化程序生成html文件。
     * 3、cms将html文件存储到GridFS中。
     * 4、cms向MQ发送页面发布消息
     * 5、MQ将页面发布消息通知给Cms Client
     * 6、Cms Client从GridFS中下载html文件
     * 7、Cms Client将html保存到所在服务器指定目录
     * <p>
     * 消息需要做幂等，用redis存储
     *
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${project.rabbitmq.queue}", durable = "true"),
            exchange = @Exchange(value = "${project.rabbitmq.exchange}"),
            key = "${project.rabbitmq.routingkey}"),
            ackMode = "MANUAL",
            errorHandler = "myErrorHandler"
    )
    public void pagePublishingConsumerListener(Message message, Channel channel) throws Exception {

        Map map = JSONObject.parseObject(new String(message.getBody()), Map.class);
        String cmsPageId = (String)map.get("cmsPageId");
        String cmsHtmlRetryMessageId = (String)map.get("cmsHtmlRetryMessageId");
        logger.info(cmsHtmlRetryMessageId+" before");

        ResponseResult templateData = restTemplate.getForObject("http://127.0.0.1:10027/cms/page/"+cmsPageId, ResponseResult.class);

        if (templateData == null || templateData.getStatus() != 200 || templateData.getData() == null) {
            throw new CustomException(CommonCode.FAILED);
        }

        HashMap<String,String> hashMap = new HashMap<>((HashMap<String,String>)templateData.getData());


        Boolean execute = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return ((StringRedisConnection) redisConnection)
                        .set(cmsHtmlRetryMessageId, cmsHtmlRetryMessageId, Expiration.seconds(60 * 60 * 24), RedisStringCommands.SetOption.ifAbsent());
            }
        });

        if(execute!=null && execute){
            logger.info(cmsHtmlRetryMessageId+" after");
            GridFSFile gridFsFile = gridFsTemplate.findOne(Query.query(GridFsCriteria.where("_id").is(hashMap.get("htmlFileId"))));
            if (gridFsFile == null) {
                throw new CustomException(CommonCode.FAILED);
            }
            GridFsResource resource = gridFsTemplate.getResource(gridFsFile);
            FileOutputStream fileOutputStream = new FileOutputStream(hashMap.get("pagePhysicalPath") + hashMap.get("pageName"));
            IOUtils.copy(resource.getInputStream(), fileOutputStream);
        }

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "test11queue", errorHandler = "myErrorHandler", ackMode = "MANUAL")
    public void testListener(Message message, Channel channel) throws Exception {
//        throw new RuntimeException("my111");
        String key = new String(message.getBody());

        String s = redisTemplate.execute((RedisCallback<String>)
                conn -> ((JedisCommands) conn.getNativeConnection())
                        .set(key, key));

        logger.info(s);


        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


}
