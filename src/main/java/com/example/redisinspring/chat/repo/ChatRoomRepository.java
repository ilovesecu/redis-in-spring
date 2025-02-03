package com.example.redisinspring.chat.repo;

import com.example.redisinspring.chat.vo.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByTitle(String title);
}
