package com.ezlinker.app.modules.entry.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.modules.entry.form.UserLoginForm;
import com.ezlinker.app.modules.user.model.User;
import com.ezlinker.app.modules.user.service.IUserService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ezlinker
 * @description: 入口
 * @author: wangwenhai
 * @create: 2019-11-11 17:44
 **/
@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    IUserService iUserService;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/login")
    public R login(@RequestBody UserLoginForm loginForm) throws XException {
        User user = iUserService.getOne(new QueryWrapper<User>().eq("username", loginForm.getUsername()));
        if (user == null) {
            throw new XException(403, "Login failure,user not exists!", "登陆失败,用户不存在");
        }
        if (!user.getPassword().toUpperCase().equals(SecureUtil.md5(loginForm.getPassword()).toUpperCase())) {
            throw new XException(403, "Login failure,password invalid!", "登陆失败,密码错误");

        }

        return new R();
    }
}
