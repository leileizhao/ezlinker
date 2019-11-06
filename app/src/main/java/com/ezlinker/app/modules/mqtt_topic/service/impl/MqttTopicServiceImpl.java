package com.ezlinker.app.modules.mqtt_topic.service.impl;

import com.ezlinker.app.modules.mqtt_topic.model.MqttTopic;
import com.ezlinker.app.modules.mqtt_topic.mapper.MqttTopicMapper;
import com.ezlinker.app.modules.mqtt_topic.service.IMqttTopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-06
 */
@Service
public class MqttTopicServiceImpl extends ServiceImpl<MqttTopicMapper, MqttTopic> implements IMqttTopicService {

}
