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

    private String action;
    private String client_id;
    private String username;
    private String reason;
}
