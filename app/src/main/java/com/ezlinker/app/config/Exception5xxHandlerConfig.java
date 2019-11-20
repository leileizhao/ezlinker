package com.ezlinker.app.config;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
