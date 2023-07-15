package com.example.chat.user.domain.request;

import com.example.chat.user.domain.entity.User;

public record UserRequest(String email, String username, String password) {

    public User toEntity() {
        return User.builder().email(email).username(username).password(password).build();
    }

}
