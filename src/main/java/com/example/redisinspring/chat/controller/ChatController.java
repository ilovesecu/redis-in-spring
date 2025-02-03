package com.example.redisinspring.chat.controller;

import com.example.redisinspring.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public ResponseEntity<Void> sendMessage(@Payload ChatMessage message){
        // 메시지를 해당 채팅방 구독자들에게 전송
        messagingTemplate.convertAndSend("/sub/room/"+message.getRoomId(), message);
        return ResponseEntity.ok().build();
    }
}
