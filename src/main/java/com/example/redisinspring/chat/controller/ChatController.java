package com.example.redisinspring.chat.controller;

import com.example.redisinspring.chat.service.ChatRoomService;
import com.example.redisinspring.chat.vo.ChatMessage;
import com.example.redisinspring.chat.vo.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;

    @PostMapping("/room/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom reqChatRoom){
        return ResponseEntity.ok().body(chatRoomService.createChatRoom(reqChatRoom));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> getAllChatRooms(){
        return ResponseEntity.ok().body(chatRoomService.getAllChatRooms());
    }

    @GetMapping("/contents/{id}")
    public ResponseEntity<List<ChatMessage>> getChatList(@PathVariable(value = "id")int id){
        ChatMessage test = new ChatMessage();
        test.setContent("TEST");
        test.setId(1L);
        test.setRoomId(id);
        test.setSender("TESTER");
        return ResponseEntity.ok().body(List.of(test));
    }

    @MessageMapping("/send")
    public ResponseEntity<Void> sendMessage(@Payload ChatMessage message){
        // 메시지를 해당 채팅방 구독자들에게 전송
        messagingTemplate.convertAndSend("/sub/room/"+message.getRoomId(), message);
        return ResponseEntity.ok().build();
    }
}
