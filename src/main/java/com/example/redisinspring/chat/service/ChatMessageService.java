package com.example.redisinspring.chat.service;

import com.example.redisinspring.chat.repo.ChatMessageRepository;
import com.example.redisinspring.chat.vo.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    //메시지 저장(비동기)
    public Mono<ChatMessage> saveMessage(Long roomId, String content, Long writerId, String sender){
        ChatMessage message = new ChatMessage(roomId, content, writerId, new Date(), sender);
        return chatMessageRepository.save(message);
    }

    //특정 채팅방의 메시지 조회(비동기)
    public Flux<ChatMessage> getMessagesByRoomId(Long roomId){
        return chatMessageRepository.findByRoomIdOrderByCreatedDateAsc(roomId);
    }
}
