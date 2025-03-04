package com.example.redisinspring.chat.vo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "chatting_content") // MongoDB 컬렉션 이름 지정
public class ChatMessage {
    @Id
    private ObjectId id;
    private String sender;
    private String content;
    private Long roomId;
    private Long writerId;
    private String type; // "CHAT", "JOIN", "LEAVE"
    private Date createdDate;

    public ChatMessage(Long roomId, String content, Long writerId, Date date, String sender) {
        this.roomId = roomId;
        this.content = content;
        this.writerId = writerId;
        this.createdDate = date;
        this.sender = sender; //원래 이부분은 MySQL에서 가져와야할수도있음 아니면 redis에 캐싱하고있거나!
    }
}
