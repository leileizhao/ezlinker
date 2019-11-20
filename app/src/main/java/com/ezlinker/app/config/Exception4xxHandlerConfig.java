package com.ezlinker.app.config;

import com.ezlinker.common.exchange.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * ezlinker
 *
 * @author wangwenhai
 * @description 4xx异常处理
 * @create 2019-11-20 23:32
 **/
@ControllerAdvice
@Slf4j
public class Exception4xxHandlerConfig {

    /**
     * 404
     *
     * @param request
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R notFountHandler(HttpServletRequest request) {
        return new R(404, "Resource not found", "资源不存在");
    }

    /**
     * 请求方式不受支持
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R methodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return new R(405, "Method:" + e.getMethod() + " Not Allowed", "HTTP请求方法:" + e.getMethod() + " 不支持");
    }

}
