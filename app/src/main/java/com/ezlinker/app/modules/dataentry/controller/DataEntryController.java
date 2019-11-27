package com.ezlinker.app.modules.dataentry.controller;

import com.ezlinker.common.exchange.R;
import com.ezlinker.emqintegeration.message.ConnectedMessage;
import com.ezlinker.emqintegeration.message.DisconnectedMessage;
import com.ezlinker.emqintegeration.message.EMQWebHookMessage;
import com.ezlinker.emqintegeration.message.PublishMessage;
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
    @PostMapping("/connected")
    public R connected(@RequestBody ConnectedMessage message) {

        System.out.println("RMQ Data:" + message);
        return new R();
    }

    @PostMapping("/disconnected")
    public R disconnected(@RequestBody DisconnectedMessage message) {

        System.out.println("RMQ Data:" + message);
        return new R();
    }

    @PostMapping("/publish")
    public R publish(@RequestBody PublishMessage message) {

        System.out.println("RMQ Data:" + message);
        return new R();
    }



}
