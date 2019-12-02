package com.ezlinker.app.modules.mqtttopic.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ezlinker.app.common.AbstractXController;
import com.ezlinker.app.modules.mqtttopic.model.MqttTopic;
import com.ezlinker.app.modules.mqtttopic.service.IMqttTopicService;
import com.ezlinker.common.exception.XException;
import com.ezlinker.common.exchange.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * MQTT的TOPIC记录 前端控制器
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-18
 */
@RestController
@RequestMapping("/mqtttopics")
public class MqttTopicController extends AbstractXController<MqttTopic> {

    @Autowired
    IMqttTopicService iMqttTopicService;

    public MqttTopicController(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    protected R get(Long id) throws XException {
        return super.get(id);
    }

    /**
     * 获取某个模块的所有Topic列表
     *
     * @param clientId
     * @return
     */
    @GetMapping
    public R queryForList(@RequestParam String clientId) {

        List<MqttTopic> list = iMqttTopicService.list(new QueryWrapper<MqttTopic>()
                .eq("client_id", clientId)
                .orderByDesc("create_time"));
        return data(list);
    }
}

