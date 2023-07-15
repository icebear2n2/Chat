package com.example.chat.user.service;

import com.example.chat.user.domain.entity.User;
import com.example.chat.user.domain.request.UserRequest;
import com.example.chat.user.domain.response.UserResponse;
import com.example.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //TODO: CRUD

    public void save(UserRequest request) {
        User save = userRepository.save(request.toEntity());
        log.info("SAVED_ID : {}, SAVED_EMAIL : {}, SAVED_USERNAME: {}", save.getId(), save.getEmail(), save.getUsername());
    }

    public List<UserResponse> findAll() {
        List<User> all = userRepository.findAll();
        return all.stream().map(UserResponse::new).toList();
    }

    public UserResponse findById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElseThrow(() -> new RuntimeException("NOT FOUND MEMBER BY " + id));
        return new UserResponse(user);
    }

    public UserResponse update(Long id, UserRequest request) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isEmpty()) throw  new RuntimeException("없는 유저를 수정하려 합니다.");
        User user = new User(id, request.email(), request.username(), null, null, null, null);
        User save = userRepository.save(user);

        return new UserResponse(save);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
