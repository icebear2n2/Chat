package com.example.chat.room.domain.response;

//import com.example.chat.message.domain.entity.Message;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RoomResponse {
    private Long id;
    private String roomName;

    private int maxCapacity;

    private List<User> users;
//    private List<UserDto> users;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.maxCapacity = room.getMaxCapacity();
        this.users = room.getUsers();
    }
}
