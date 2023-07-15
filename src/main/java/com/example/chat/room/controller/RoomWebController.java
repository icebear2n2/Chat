package com.example.chat.room.controller;

import com.example.chat.room.domain.response.RoomResponse;
import com.example.chat.room.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/room")
@RequestMapping("/")
public class RoomWebController {

    private final RoomService service;
    @GetMapping("room")
    public String getRoomList(Model model, HttpSession session) {
        List<RoomResponse> all = service.findAll();
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("rooms", all);
        model.addAttribute("userId", userId);
        return "/room/roomList";
    }

    @GetMapping("{id}")
    public String chattingRoom(@PathVariable String id, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        if (userId == null) {
            // 로그인되지 않은 사용자가 채팅방에 접속하려고 할 때 처리할 로직 작성
            // 예를 들어 로그인 페이지로 리다이렉트하거나 에러 페이지를 표시할 수 있습니다.
            return "redirect:/login";
        }

        // id에 따른 채팅방 접근 권한 체크 로직 작성
        // 예를 들어, 특정 조건에 따라 허용된 채팅방만 접근하도록 할 수 있습니다.

        model.addAttribute("name", username);
        return "/room/chattingRoom";
    }


}
