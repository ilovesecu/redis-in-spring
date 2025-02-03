package com.example.redisinspring.chat.service;

import com.example.redisinspring.chat.repo.ChatRoomRepository;
import com.example.redisinspring.chat.vo.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom(ChatRoom reqChatRoom){
        ChatRoom save = chatRoomRepository.save(reqChatRoom);
        return save;
    }

    public List<ChatRoom> getAllChatRooms(){
        return chatRoomRepository.findAll();
    }

    public Optional<ChatRoom> getChatRoomByTitle(String title){
        return chatRoomRepository.findByTitle(title);
    }
}
