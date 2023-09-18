package com.hncpu.blog.exception;

import com.hncpu.blog.common.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常拦截 
    @ExceptionHandler
    public ApiResult handlerException(Exception e) {
        e.printStackTrace();
        return ApiResult.error(e.getMessage());
    }
}