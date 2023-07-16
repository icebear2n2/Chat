package com.example.chat.user.domain.response;

//import com.example.chat.message.domain.entity.Message;
import com.example.chat.user.domain.entity.User;
import lombok.Getter;

@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String username;
//    private List<RoomDto> rooms;


    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
