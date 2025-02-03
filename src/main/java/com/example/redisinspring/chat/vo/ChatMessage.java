package com.example.redisinspring.chat.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessage {
    private Long id;
    private String sender;
    private String content;
    private String roomId;
    private String type; // "CHAT", "JOIN", "LEAVE"
}
