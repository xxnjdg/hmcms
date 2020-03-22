package com.xxnjdg.heimacms.common.exception;

import com.xxnjdg.heimacms.common.model.response.ResponseCode;
import com.xxnjdg.heimacms.common.model.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;


/**
 * 全局异常处理
 *
 * @author djr
 * @version 1.0
 * @date 2020/3/9 11:47
 */
@RestControllerAdvice
@Slf4j
public class ExceptionCatch {

    /**
     * @param customException 自定义运行时异常
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException){
        System.out.println("ssssssssssssssss");
        customException.printStackTrace();
        System.out.println("ssssssssssssssss");
        return new ResponseResult(customException.getResponseCode());
    }

    // TODO: 2020/2/2 没有捕获 Exception ，会默认走spring mvc异常处理


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("++++++++++++++++++++++++++++++++");
        log.info(e.getMessage());
        log.error("++++++++++++++++++++++++++++++++");
        return new ResponseResult(new ResponseCode() {
            @Override
            public int status() {
                return 0;
            }

            @Override
            public String statusText() {
                return e.getBindingResult().getFieldError().getDefaultMessage();
            }
        });
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseResult handleValidationException(ValidationException e) {
        log.error("++++++++++++++++++++++++++++++++");
        log.error(e.getMessage(), e);
        log.error("++++++++++++++++++++++++++++++++");
        return new ResponseResult(new ResponseCode() {
            @Override
            public int status() {
                return 0;
            }

            @Override
            public String statusText() {
                return e.getCause().getMessage();
            }
        });
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseResult handleConstraintViolationException(ConstraintViolationException e) {
        log.error("++++++++++++++++++++++++++++++++");
        log.error(e.getMessage(), e);
        log.error("++++++++++++++++++++++++++++++++");
        return new ResponseResult(new ResponseCode() {
            @Override
            public int status() {
                return 0;
            }

            @Override
            public String statusText() {
                return e.getMessage();
            }
        });
    }

}
