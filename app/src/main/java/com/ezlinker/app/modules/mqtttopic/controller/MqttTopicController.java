package com.ezlinker.app.modules.mqtttopic.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.ezlinker.app.common.AbstractXController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * MQTT的TOPIC记录 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/mqtttopic")
public class MqttTopicController extends AbstractXController {

    public MqttTopicController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }
}

