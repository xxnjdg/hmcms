package com.xxnjdg.heimacms.common.model.aop;

import lombok.Data;

import java.util.Date;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Data
public class OperationLog {
    private String operationId;
    private String operationModel;
    private String operationType;
    private String operationDesc;
    private String operationRequestParam;
    private String operationResponseParam;
    private String operationUserId;
    private String operationUserName;
    private String operationMethod;
    private String operationUri;
    private String operationIp;
    private Date operationCreateTime;
    private String operationVersion;
}
