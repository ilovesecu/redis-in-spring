package com.example.redisinspring.chat.repo;

import com.example.redisinspring.chat.vo.ChatMessage;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveMongoRepository<ChatMessage, ObjectId> {
    Flux<ChatMessage> findByRoomIdOrderByCreatedDateAsc(Long roomId); // 특정 채팅방 메시지 조회
}
