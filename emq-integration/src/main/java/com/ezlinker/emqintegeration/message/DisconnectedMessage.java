package com.ezlinker.emqintegeration.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: ezlinker
 * @description: 离线消息
 * @author: wangwenhai
 * @create: 2019-11-21 11:11
 **/
@EqualsAndHashCode(callSuper = false)
@Data
public class DisconnectedMessage extends EMQWebHookMessage{

    /**
     * 触发的动作
     */
    private String action;
    /**
     * 客户端ID
     */
    private String clientId;
    /**
     * MQTT Username
     */
    private String username;
    /**
     * 离线原因
     */
    private String reason;
    /**
     * IP地址
     */
    private String ip;

    @Override
    public String toString() {
        return "DisconnectedMessage{" +
                "action='" + action + '\'' +
                ", clientId='" + clientId + '\'' +
                ", username='" + username + '\'' +
                ", reason='" + reason + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
