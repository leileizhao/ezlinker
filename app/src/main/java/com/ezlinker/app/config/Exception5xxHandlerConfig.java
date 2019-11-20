package com.ezlinker.app.config;

import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ezlinker
 *
 * @author wangwenhai
 * @description 5xx处理
 * @create 2019-11-20 23:31
 **/
@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class Exception5xxHandlerConfig {

    /**
     * XException
     */
    @ExceptionHandler(XException.class)
    @ResponseBody
    public R handXException(XException e) {
        log.error(e.getClass() + ":" + e.getMessage());
        Integer code = e.getCode();
        String message = e.getMessage();
        String i8nMessage = e.getI18nMessage();
        return new R(code, message, i8nMessage, null);
    }


    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R handException(Exception e) {
        log.error(e.getClass() + ":" + e.getMessage());
        return new R(500, "Server internal error", "服务器内部错误", null);
    }


}
