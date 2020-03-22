package com.xxnjdg.heimacms.common.model.aop;

import lombok.Data;

import java.util.Date;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Data
public class ExceptionLog {
    private String exceptionId;
    private String exceptionRequestParam;
    private String exceptionName;
    private String exceptionMessage;
    private String operationUserId;
    private String operationUserName;
    private String operationMethod;
    private String operationUri;
    private String operationIp;
    private String operationVersion;
    private Date operationCreateTime;
}
