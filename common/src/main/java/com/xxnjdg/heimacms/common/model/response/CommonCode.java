package com.xxnjdg.heimacms.common.model.response;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public enum CommonCode implements ResponseCode {
    SUCCESS(200,"success"),
    FAILED(500,"failed");

    private int status;
    private String statusText;

    /**
     * @param status 代码
     * @param statusText 消息
     */
    CommonCode(int status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    @Override
    public int status() {
        return status;
    }

    @Override
    public String statusText() {
        return statusText;
    }
}
