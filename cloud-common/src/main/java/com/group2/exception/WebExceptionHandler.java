package com.group2.exception;

import com.group2.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YangPx
 * @create 2021-10-28 08:42
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {
    @ExceptionHandler
    public CommonResult handle1(RuntimeException e) {
        return new CommonResult(500, "RuntimeException", e);
    }

    @ExceptionHandler
    public CommonResult handle1(MethodArgumentNotValidException e) {
        return new CommonResult(500, "MethodArgumentNotValidException", e);
    }

    @ExceptionHandler
    public CommonResult handle1(Exception e) {
        return new CommonResult(500, "Exception", e);
    }
}
