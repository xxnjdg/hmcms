package com.xxnjdg.heimacms.common.exception;

import com.xxnjdg.heimacms.common.model.response.ResponseCode;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
public class CustomException extends RuntimeException {

    private ResponseCode responseCode;

    public CustomException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
