package com.example.chat.message.service;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.message.domain.request.MessageRequest;
import com.example.chat.message.repository.MessageRepository;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.room.repository.RoomRepository;
import com.example.chat.user.domain.entity.User;
import com.example.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void createMessage(MessageRequest request) {
        Message message = new Message(
                null,
                request.content(),
                null,
                User.builder().id(request.userId()).build(),
                Room.builder().id(request.roomId()).build()
        );

        messageRepository.save(message);

    }

    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
