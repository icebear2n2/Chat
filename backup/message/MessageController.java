package com.example.chat.message;

import com.example.chat.message.domain.entity.Message;
import com.example.chat.message.domain.request.MessageRequest;
import com.example.chat.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService service;

    @PostMapping
    public void createMessage(@RequestBody MessageRequest request) {
        service.createMessage(request);
    }

    @GetMapping
    public List<Message> findAll() {
        return service.findAll();
    }
}
