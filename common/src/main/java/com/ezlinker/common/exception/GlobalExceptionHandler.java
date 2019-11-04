package com.ezlinker.common.exception;

import com.ezlinker.common.exchange.R;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: ezlinker
 * @description: 全局异常处理器
 * @author: wangwenhai
 * @create: 2019-09-12 16:43
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handException(Exception e, HttpServletRequest httpServletRequest) {
        logger.error("异常产生:", e.getMessage());
        e.printStackTrace();
        return new R();
    }
}
