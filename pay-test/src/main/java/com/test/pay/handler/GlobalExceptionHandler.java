package com.test.pay.handler;

import com.test.pay.response.ResponseBody;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一异常处理
 * @author Liu
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @org.springframework.web.bind.annotation.ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseBody handleException(RuntimeException exception) {
        ResponseBody result = new ResponseBody(exception);
        return result;
    }
}
