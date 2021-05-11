package com.eltavi.anonchat.controller;

import com.eltavi.anonchat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messagingTemplate.convertAndSend(
                "/topic/" + chatMessage.getRoomCode(),
                chatMessage
        );
    }

    @MessageMapping("/chat.addUser")
    public void addUserIntoRoom(@Payload ChatMessage chatMessage,
                                       SimpMessageHeaderAccessor headerAccessor) {
        String room = chatMessage.getRoomCode();
        headerAccessor.getSessionAttributes().put("roomCode", room);
    }

}
