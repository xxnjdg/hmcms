package com.xxnjdg.heimacms.common.model.response;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public interface ResponseCode {

    /**
     * 返回状态码
     *
     * @return 状态码
     */
    int status();

    /**
     * 返回状态信息
     *
     * @return 状态信息
     */
    String statusText();
}
