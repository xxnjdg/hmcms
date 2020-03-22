package com.xxnjdg.heimacms.common.model.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult {

    /**
     * 返回数据
     */
    private Object data;
    /**
     * 状态码
     */
    private int status;
    /**
     * 状态信息
     */
    private String statusText;

    public ResponseResult(ResponseCode responseCode){
        this.status = responseCode.status();
        this.statusText = responseCode.statusText();
    }

    public ResponseResult(ResponseCode responseCode,Object data){
        this.status = responseCode.status();
        this.statusText = responseCode.statusText();
        this.data = data;
    }

}

