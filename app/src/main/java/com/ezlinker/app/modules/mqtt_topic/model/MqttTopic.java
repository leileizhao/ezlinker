package com.ezlinker.app.modules.mqtt_topic.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ezlinker.common.model.XEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangwenhai
 * @since 2019-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ez_mqtt_topic")
public class MqttTopic extends XEntity {

    private static final long serialVersionUID=1L;

    private String topic;

    private Integer access;

    private String ip;

    private Integer allow;


}
