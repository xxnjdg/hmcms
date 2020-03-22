package com.xxnjdg.heimacms.servicemodel.cmsservice;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@ApiModel("CmsHtmlRetryMessage实体")
@Document(collection = "cms_html_retry_message")
@Data
@NoArgsConstructor
public class CmsHtmlRetryMessage {

    public static final String ORDER_SENDING = "0";

    public static final String ORDER_SEND_SUCCESS = "1";

    public static final String ORDER_SEND_RETRY = "2";

    public static final String ORDER_SEND_FAILURE = "3";

    public static final int ORDER_TIMEOUT = 1;

    @Id
    @ApiModelProperty("主键")
    private String messageId;
    @ApiModelProperty("mq消息")
    private String message;
    @ApiModelProperty("重试次数")
    private Integer tryCount;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("下一次重试时间")
    private Date nextRetry;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("更新时间")
    private Date updateTime;

    public CmsHtmlRetryMessage(String message){
        this.message = message;
        this.tryCount = 0;
        this.status = CmsHtmlRetryMessage.ORDER_SENDING;
        this.createTime = new Date();
        this.updateTime = new Date();
    }
}
