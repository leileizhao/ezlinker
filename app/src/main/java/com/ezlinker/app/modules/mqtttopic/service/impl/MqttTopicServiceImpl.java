package com.ezlinker.app.modules.mqtttopic.service.impl;

import com.ezlinker.app.modules.mqtttopic.model.MqttTopic;
import com.ezlinker.app.modules.mqtttopic.mapper.MqttTopicMapper;
import com.ezlinker.app.modules.mqtttopic.service.IMqttTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * MQTT的TOPIC记录 服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-18
 */
@Service
public class MqttTopicServiceImpl extends ServiceImpl<MqttTopicMapper, MqttTopic> implements IMqttTopicService {

}
