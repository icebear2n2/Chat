package com.example.chat.room.domain.request;

import com.example.chat.room.domain.entity.Room;

public record RoomRequest(String roomName, int maxCapacity) {

    public Room toEntity() {
        return Room.builder().roomName(roomName).maxCapacity(maxCapacity).build();
    }
}
