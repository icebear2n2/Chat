package com.example.chat.user.domain.response;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private List<RoomDto> rooms;


    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.rooms = user.getMessagesRooms().stream().map(Message::getRoom).map(RoomDto::new).toList();
    }


    @Getter
    @AllArgsConstructor
    class RoomDto {
        private Long id;
        private String roomName;

        public RoomDto(Room room) {
            this.id = room.getId();
            this.roomName = room.getRoomName();
        }
    }
}
