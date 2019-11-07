package com.ezlinker.common.exchange;

import lombok.Data;

/**
 * @program: ezlinker
 * @description: 统一返回结果
 * @author: wangwenhai
 * @create: 2019-11-04 17:40
 **/
@Data
public class R {

    /**
     * 应用名
     */
    private String appName = "ezlinker";
    /**
     * 代码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 国际化提示
     */
    private String i18nMessage;
    /**
     * 数据
     */
    private Object data;

    public R() {
    }

    public R(Integer code) {
        this.code = code;
    }

    public R(String message) {
        this.message = message;
    }

    public R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public R(Integer code, String message, Object data) {

        this.code = code;
        this.message = message;
        this.data = data;
    }

    public R(Object data) {
        this.code = RCode.SUCCESS.getCode();
        this.message = RCode.SUCCESS.getMessage();
        this.i18nMessage = RCode.SUCCESS.getI8nMessage();
        this.data = data;
    }

    public R(Integer code, String message, String i8nMessage, Object data) {
        this.code = code;
        this.message = message;
        this.i18nMessage = i8nMessage;
        this.data = data;
    }
}
