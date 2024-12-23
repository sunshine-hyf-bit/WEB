package org.example.controller.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/ws")
    @SendTo("/topic/messages")
    public String sendMessage(String message) throws Exception {
        // 模拟处理
        return message;
    }
}

