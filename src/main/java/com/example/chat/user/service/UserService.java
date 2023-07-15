package com.example.chat.user.service;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.message.repository.MessageRepository;
import com.example.chat.room.domain.entity.Room;
import com.example.chat.room.repository.RoomRepository;
import com.example.chat.user.domain.entity.User;
import com.example.chat.user.domain.request.ConnectRequest;
import com.example.chat.user.domain.request.LoginRequest;
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
    private final RoomRepository roomRepository;

    private final MessageRepository messageRepository;

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
        User user = byId.orElseThrow(() -> new RuntimeException("NOT FOUND USER BY " + id));
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

    public void joinRoom(ConnectRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("NOT FOUND USER BY " + request.userId()));
        Room room = roomRepository.findById(request.roomId()).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + request.roomId()));

        user.getRooms().add(room);
        userRepository.save(user);
    }

    public void leaveRoom(ConnectRequest request) {
        User user = userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("NOT FOUND USER BY " + request.userId()));
        Room room = roomRepository.findById(request.roomId()).orElseThrow(() -> new RuntimeException("NOT FOUND ROOM BY " + request.roomId()));

        user.getRooms().remove(room);
        userRepository.save(user);

        List<Message> messages = messageRepository.findByUserAndRoom(user, room);
        messageRepository.deleteAll(messages);
    }

    public User login(LoginRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.email());
        User user = byEmail.orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.password())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

}
