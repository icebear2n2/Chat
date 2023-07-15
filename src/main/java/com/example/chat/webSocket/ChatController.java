package com.example.chat.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class ChatController {
    public ChatController() {
    }

    @GetMapping({"/"})
    public String index() {
        return "error";
    }

//    @GetMapping({"/{id}"})
//    public String chattingRoom(@PathVariable String id, Model model) {
//        if (id.equals("guest")) {
//            model.addAttribute("name", " ");
//        } else if (id.equals("master")) {
//            model.addAttribute("name", "master");
//        } else {
//            if (!id.equals("loose")) {
//                return "error";
//            }
//
//            model.addAttribute("name", "loose");
//        }
//
//        return "chattingRoom";
//    }
}
