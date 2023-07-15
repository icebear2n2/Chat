package com.example.chat.room.domain.request;

import com.example.chat.room.domain.entity.Room;

public record RoomRequest(String roomName) {

    public Room toEntity() {
        return Room.builder().roomName(roomName).build();
    }
}
