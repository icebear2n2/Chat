package com.example.chat.room.domain.request;

import com.example.chat.room.domain.entity.Room;

public record RoomRequest(Long id, String roomName, int maxCapacity) {

    public Room toEntity() {

        return Room.builder().id(id).roomName(roomName).maxCapacity(maxCapacity).build();
    }
}
