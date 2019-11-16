package com.ezlinker.app.config;

import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
public class GlobalExceptionHandlerConfig {
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
        String context = request.getContextPath();
        String method = request.getMethod();
        String path = request.getRequestURI();

        return new R(404, "Resource not found", "资源不存在", method + context + ":" + path);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(XException.class)
    @ResponseBody
    public R handXException(XException e) {
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
        e.printStackTrace();
        return new R(500, "Server internal error", "服务器内部错误", null);
    }


    /**
     * 参数缺失
     *
     * @param e
     * @return
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R handMissingServletRequestParameterException(MissingServletRequestParameterException e) {

        return new R(400, e.getMessage(), e.getParameterType() + "类型的参数:'" + e.getParameterName() + "'缺失", null);
    }


    /**
     * 参数验证失败
     *
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R bindExceptionHandler(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        StringBuilder buffer = new StringBuilder();
        buffer.append("[");
        for (ObjectError error : errors) {
            buffer.append(error.getDefaultMessage()).append(";");
        }
        buffer.append("]");
        return new R(400, "Invalid param!", buffer.toString(), null);

    }
}
