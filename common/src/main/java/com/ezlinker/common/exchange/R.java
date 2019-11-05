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
    private Integer code;
    private String message;
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
        this.data = data;
    }
}
