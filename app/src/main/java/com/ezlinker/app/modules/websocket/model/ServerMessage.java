package com.ezlinker.app.modules.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ezlinker
 *
 * @author wangwenhai
 * @description 服务端消息
 * @create 2019-11-27 22:31
 **/
@Data
@AllArgsConstructor
public class ServerMessage {

    private String content;

    public ServerMessage() {
    }

    @Override
    public String toString() {
        return content;
    }

}