package com.muditasoft.chatroom.controller;

import com.alibaba.fastjson.JSON;
import com.muditasoft.chatroom.model.Message;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat")
public class WebSocketChatServer {
    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();


    /**
     * Send message to each active session.
     */
    private static void sendMessageToAll(String msg) {
        onlineSessions.forEach(((s, session) -> {
            try {
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        //TODO: add on open connection.
        onlineSessions.put(session.getId(), session);

        Message message = new Message(session.getId(), onlineSessions.size(), "New Open", "ENTER");
        String jsonMessage = JSON.toJSONString(message);

        sendMessageToAll(jsonMessage);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        //TODO: add send message.
        Message message = JSON.parseObject(jsonStr, Message.class);

        String msg = message.getMessage();
        String username = message.getUsername();

        Message newMessage = new Message(username, onlineSessions.size(), msg, "SPEAK");
        String jsonMessage = JSON.toJSONString(newMessage);
        sendMessageToAll(jsonMessage);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        //TODO: add close connection.
        onlineSessions.remove(session.getId());

        Message message = new Message(session.getId(), onlineSessions.size(), "Leave", "LEAVE");
        String jsonMessage = JSON.toJSONString(message);
        sendMessageToAll(jsonMessage);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }
}
