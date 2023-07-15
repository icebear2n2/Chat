package com.example.chat.user.repository;


import com.example.chat.user.domain.entity.User;
import com.example.chat.user.domain.request.LoginRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}
