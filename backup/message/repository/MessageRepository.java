package com.example.chat.message.repository;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUserAndRoom(User user, Room room);
}
