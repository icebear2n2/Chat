package com.example.chat.user.controller;

import com.example.chat.user.domain.request.ConnectRequest;
import com.example.chat.user.domain.request.UserRequest;
import com.example.chat.user.domain.response.UserResponse;
import com.example.chat.user.repository.UserRepository;
import com.example.chat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public void save(@RequestBody UserRequest request) {
        service.save(request);
    }

    @PostMapping("/joinRoom")
    public void joinRoom(@RequestBody ConnectRequest request) {
        service.joinRoom(request);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public UserResponse findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PutMapping("{id}")
    public UserResponse update(@PathVariable("id") Long id, @RequestBody UserRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @DeleteMapping("/leaveRoom")
    public void leaveRoom(@RequestBody ConnectRequest request) {
        service.leaveRoom(request);
    }
}
