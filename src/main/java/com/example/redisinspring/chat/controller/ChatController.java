package com.example.redisinspring.chat.controller;

import com.example.redisinspring.chat.service.ChatMessageService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @PostMapping("/room/create")
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoom reqChatRoom){
        return ResponseEntity.ok().body(chatRoomService.createChatRoom(reqChatRoom));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<ChatRoom>> getAllChatRooms(){
        return ResponseEntity.ok().body(chatRoomService.getAllChatRooms());
    }

    //특정 채팅방의 메시지 조회
    @GetMapping("/contents/{roomId}")
    public Flux<ChatMessage> getChatList(@PathVariable(value = "roomId")long roomId){
        return chatMessageService.getMessagesByRoomId(roomId);
    }

    //메시지 저장(테스트용)
    @PostMapping("/save")
    public Mono<ChatMessage> saveMessage(@RequestBody ChatMessage chatMessage){
        return chatMessageService.saveMessage(chatMessage.getRoomId(), chatMessage.getContent(), 1L, chatMessage.getSender());
    }

    @MessageMapping("/send")
    public Mono<ResponseEntity<Void>> sendMessage(@Payload ChatMessage message){
        //메시지 MongoDB에 저장
        return chatMessageService.saveMessage(message.getRoomId(), message.getContent(), message.getWriterId(), message.getSender()).flatMap(savedMsg -> {
            // 메시지를 해당 채팅방 구독자들에게 전송
            messagingTemplate.convertAndSend("/sub/room/"+savedMsg.getRoomId(), savedMsg);
            return Mono.just(ResponseEntity.ok().build());
        });
    }
}
