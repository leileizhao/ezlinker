package com.ezlinker.app.modules.dataentry.controller;

import com.ezlinker.common.exchange.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ezlinker
 * @description: EMQ数据入口
 * @author: wangwenhai
 * @create: 2019-11-21 10:39
 **/
@RestController
@RequestMapping("/data")
public class DataEntryController {
    @PostMapping("/in")
    public R in(@RequestBody Object data) {

        System.out.println("RMQ Data:" + data.getClass());
        return new R();
    }
}
