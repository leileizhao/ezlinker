package com.ezlinker.emqintegeration.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: ezlinker
 * @description: 消息发送成功
 * @author: wangwenhai
 * @create: 2019-11-21 11:12
 **/
@Data
@EqualsAndHashCode(callSuper = false)

public class PublishMessage extends EMQWebHookMessage {
    private String action;
    private String clientid;
    private String username;
    private String topic;
    private int qos;
    private boolean retain;
    private String payload;
    private long timestamp;

    @Override
    public String toString() {
        return "PublishMessage{" +
                "action='" + action + '\'' +
                ", clientid='" + clientid + '\'' +
                ", username='" + username + '\'' +
                ", topic='" + topic + '\'' +
                ", qos=" + qos +
                ", retain=" + retain +
                ", payload='" + payload + '\'' +
                ", timeStrap=" + timestamp +
                '}';
    }
}
