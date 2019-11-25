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

    private String action;
    private String client_id;
    private String username;
    private int keepalive;
    private String ipaddress;
    private int proto_ver;
    private long connected_at;
    private int conn_ack;

}
