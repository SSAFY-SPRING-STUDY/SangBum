package com.example.practice1.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SessionManager {

    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public String createSession(Long memberId) {
        String token = UUID.randomUUID().toString();
        sessionStore.put(token, memberId);
        return token;
    }

    public Long getMemberId(String token) {
        return sessionStore.get(token);
    }

    public void removeSession(String token) {
        sessionStore.remove(token);
    }
}