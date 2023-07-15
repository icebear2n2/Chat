package com.example.chat.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final ChatHandler chatHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.chatHandler, new String[]{"ws/chat"}).setAllowedOrigins(new String[]{"*"});
    }

    public WebSocketConfig(final ChatHandler chatHandler) {
        this.chatHandler = chatHandler;
    }
}

