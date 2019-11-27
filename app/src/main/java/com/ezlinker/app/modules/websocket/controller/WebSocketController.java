package com.ezlinker.app.modules.websocket.controller;

import com.ezlinker.app.modules.websocket.model.ClientMessage;
import com.ezlinker.app.modules.websocket.model.ServerMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ezlinker
 *
 * @author wangwenhai
 * @description Websocket入口
 * @create 2019-11-27 22:27
 **/
@Controller
public class WebSocketController {

    @Resource
    SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 入口
     * * stompClient.send("/ws", {}, JSON.stringify({'name': name}));
     *
     * @param message
     * @return
     * @throws Exception
     */

    @MessageMapping("/ws")
    /**
     * 反馈到订阅了 /data topic的客户端
     */
    @SendTo("/broker/data")
    public ServerMessage greeting(ClientMessage message) throws Exception {
        return new ServerMessage("Hello, you are success~!" + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    /**
     * 定时推送消息
     */
    @Scheduled(fixedRate = 5000)
    public void callback() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpMessagingTemplate.convertAndSend("/topic/callback", "定时推送消息时间: " + df.format(new Date()));
    }
    /**
     * stompClient.send("/welcome", {}, JSON.stringify({'name': name}));
     *
     * function connect() {
     *         var socket = new SockJS('http://localhost:8092/stomp');
     *         stompClient = Stomp.over(socket);
     *         stompClient.connect({}, function (frame) {
     *             setConnected(true);
     *             console.log('Connected:' + frame);
     *             stompClient.subscribe('/broker/data', function (response) {
     *                 showResponse(JSON.parse(response.body).responseMessage);
     *             });
     *             stompClient.subscribe('/broker/callback', function (response) {
     *                 showCallback(response.body);
     *             });
     *         });
     *     }
     */
}
