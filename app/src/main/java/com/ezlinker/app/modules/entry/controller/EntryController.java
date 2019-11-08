package com.ezlinker.app.modules.entry.controller;

import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ezlinker
 * @description: 登陆注销入口
 * @author: wangwenhai
 * @create: 2019-11-08 15:34
 **/
@RestController
@RequestMapping("/entry")
public class EntryController {
    @PostMapping("/register")
    public R register(Object o) {
        return null;
    }

}
