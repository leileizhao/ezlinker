package com.ezlinker.app.modules.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ezlinker
 *
 * @author wangwenhai
 * @description 客户端消息
 * @create 2019-11-27 22:31
 **/
@Data
@AllArgsConstructor
public class ClientMessage {

    private String name;

    public ClientMessage() {

    }

}