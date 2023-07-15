package com.example.chat.room.domain.response;

import com.example.chat.room.domain.entity.Room;
import lombok.Getter;

@Getter
public class RoomResponse {
    private Long id;
    private String roomName;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
    }
}
