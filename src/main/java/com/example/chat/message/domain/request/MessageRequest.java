package com.example.chat.message.domain.request;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.user.domain.entity.User;

public record MessageRequest(Long userId, Long roomId, String content) {

}
