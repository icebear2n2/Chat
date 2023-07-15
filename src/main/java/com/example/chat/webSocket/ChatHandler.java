package com.example.chat.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {

    private static LinkedHashSet<WebSocketSession> numSet = new LinkedHashSet();

    public ChatHandler() {
    }

    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (numSet.size() >= 3) {
            WebSocketSession oldSession = (WebSocketSession)numSet.iterator().next();
            oldSession.sendMessage(new TextMessage("채팅이 종료되었습니다."));
            numSet.remove(numSet.iterator().next());
        }

        boolean isSessionAlive = false;
        Iterator var4 = numSet.iterator();

        WebSocketSession sess;
        while(var4.hasNext()) {
            sess = (WebSocketSession)var4.next();
            if (sess.getId().equals(session.getId())) {
                isSessionAlive = true;
            }
        }

        if (isSessionAlive) {
            var4 = numSet.iterator();

            while(var4.hasNext()) {
                sess = (WebSocketSession)var4.next();
                sess.sendMessage(message);
            }
        }

    }

    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        numSet.add(session);
    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        numSet.remove(session);
    }
}
