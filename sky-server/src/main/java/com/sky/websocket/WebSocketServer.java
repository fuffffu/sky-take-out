package com.sky.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@ServerEndpoint("/ws")  // ws://localhost:8080/ws
public class WebSocketServer {

    private static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        log.info("客户端连接：{}", session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        log.info("客户端断开连接：{}", session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到客户端消息：{}", message);
    }

    public void sendToAllClient(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("推送失败", e);
            }
        }
    }
}