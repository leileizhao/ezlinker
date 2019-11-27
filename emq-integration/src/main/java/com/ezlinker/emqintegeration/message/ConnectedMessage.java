package com.ezlinker.emqintegeration.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: ezlinker
 * @description: mqtt客户端连接成功
 * @author: wangwenhai
 * @create: 2019-11-21 11:10
 **/
@Data
@EqualsAndHashCode(callSuper = false)

public class ConnectedMessage extends EMQWebHookMessage{

    /**
     * 触发的动作
     */
    private String action;
    /**
     * 客户端ID
     */
    private String clientid;
    /**
     * MQTT Username
     */
    private String username;
    /**
     * IP地址
     */
    private String ip;

    @Override
    public String toString() {
        return "ConnectedMessage{" +
                "action='" + action + '\'' +
                ", clientId='" + clientid + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
