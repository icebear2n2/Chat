package com.example.chat.user.controller;

import com.example.chat.user.domain.entity.User;
import com.example.chat.user.domain.request.ConnectRequest;
import com.example.chat.user.domain.request.LoginRequest;
import com.example.chat.user.domain.request.UserRequest;
import com.example.chat.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserWebController {

    private final UserService service;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Validated UserRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return "/user/signup";
        }

        try {
            service.save(request);
            return "redirect:/user/login";
        } catch (RuntimeException e) {
            result.rejectValue("email", "error email", "FAILED TO SIGNUP USER!! : " +  e.getMessage());
            return "/user/signup";
        }
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String  login(@ModelAttribute("user") @Validated LoginRequest request, HttpSession session, BindingResult result) {
        if (result.hasErrors()) {

            return "/user/login";
        }

        User login = service.login(request);
        if (login != null) {
            session.setAttribute("userId", login.getId());
            session.setAttribute("email", login.getEmail());
            session.setAttribute("username", login.getUsername());
        }

        return "redirect:/room";
    }

    @PostMapping("/join/{id}")
    public String joinRoom(@PathVariable("id") Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            // 세션에 userId가 없는 경우 처리할 로직 작성
            // 예를 들어 로그인 페이지로 리다이렉트 또는 에러 페이지를 표시할 수 있습니다.
            return "redirect:/user/login";
        }

        ConnectRequest connectRequest = new ConnectRequest(userId, id);
        try {
            service.joinRoom(connectRequest);
            return "redirect:/{id}";
        } catch (RuntimeException e) {
            // joinRoom에서 예외가 발생한 경우 처리할 로직 작성
            // 예를 들어 에러 페이지를 표시하거나 다른 페이지로 리다이렉트할 수 있습니다.
            return "redirect:/error";
        }
    }





}
