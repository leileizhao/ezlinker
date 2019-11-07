package com.ezlinker.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: ezlinker
 * @description: 通用异常
 * @author: wangwenhai
 * @create: 2019-09-12 16:46
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class XException extends Exception {
    private String message ;
    private Integer code ;

    public XException() {
    }

    public XException(String message) {
        this.message = message;
    }

    public XException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public XException(String message, String message1, Integer code) {
        super(message);
        this.message = message1;
        this.code = code;
    }

    public XException(String message, Throwable cause, String message1, Integer code) {
        super(message, cause);
        this.message = message1;
        this.code = code;
    }

    public XException(Throwable cause, String message, Integer code) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    public XException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, Integer code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
    }
}
