package com.example.redisinspring.kafka.service;

import com.example.redisinspring.chat.vo.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC_NAME = "chatting";
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper om = new ObjectMapper();

    public void send(ChatMessage chatMessage){
        try {
            String toJson = om.writeValueAsString(chatMessage);
            kafkaTemplate.send(TOPIC_NAME, toJson);
        }catch (Exception e){
            throw new RuntimeException("예외 발생! : "+ e.getMessage());
        }
    }
}
