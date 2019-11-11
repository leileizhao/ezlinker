package com.ezlinker.app.configs;

import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: ezlinker
 * @description: 全局异常处理器
 * @author: wangwenhai
 * @create: 2019-09-12 16:43
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
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
        String method = request.getMethod();
        String path = request.getRequestURI();

        return new R(404, "Resource not found", "资源不存在", method + ":" + path);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(XException.class)
    @ResponseBody
    public R handException(HttpServletRequest httpServletRequest, XException e) {
        Integer code = e.getCode();
        String message = e.getMessage();
        String i8nMessage = e.getI18nMessage();
        return new R(code, message, i8nMessage, httpServletRequest.getRequestURI());
    }

    /**
     * 参数验证失败
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R bindExceptionHandler(HttpServletRequest httpServletRequest, MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder buffer = new StringBuilder();
        buffer.append("[");
        for (ObjectError error : errors) {
            buffer.append(error.getDefaultMessage()).append(";");
        }
        buffer.append("]");
        return new R(400, "Invalid param!", buffer.toString(), "RequestPath:" + httpServletRequest.getMethod() + "->" + httpServletRequest.getRequestURI());

    }
}
