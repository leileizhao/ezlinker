package com.ezlinker.app.modules.entry.pojo;

import lombok.Data;

/**
 * @program: ezlinker
 * @description: 注册的时候提交的表单
 * @author: wangwenhai
 * @create: 2019-11-08 15:40
 **/
@Data
public class UserForm {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 重复密码
     */
    private String passwordRetry;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 邮箱验证码
     */
    private String emailVerifyCode;

}
