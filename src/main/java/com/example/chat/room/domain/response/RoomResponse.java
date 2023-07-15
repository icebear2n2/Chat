package com.example.chat.room.domain.response;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class RoomResponse {
    private Long id;
    private String roomName;
    private List<UserDto> users;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.roomName = room.getRoomName();
        this.users = room.getMessagesUsers().stream().map(Message::getUser).map(UserDto::new).toList();
    }

    @Getter
    @AllArgsConstructor
    class UserDto{
        private Long id;
        private String email;
        private String username;

        public UserDto(User user) {
            this.id = user.getId();
            this.email = user.getEmail();
            this.username = user.getUsername();
        }
    }
}
