package com.example.chat.room.controller;

import com.example.chat.room.domain.request.RoomRequest;
import com.example.chat.room.domain.response.RoomResponse;
import com.example.chat.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService service;

    @PostMapping
    public void joinRoom(@RequestBody RoomRequest request) {
        service.joinRoom(request);
    }

    @GetMapping
    public List<RoomResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public RoomResponse findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void leaveRoom(@PathVariable("id") Long id) {
        service.leaveRoom(id);
    }
}
