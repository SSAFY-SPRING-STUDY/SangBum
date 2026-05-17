package com.example.practice1.service;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.dto.login.LoginRequest;
import com.example.practice1.dto.login.LoginResponse;
import com.example.practice1.entity.MemberEntity;
import com.example.practice1.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    public AuthService(MemberRepository memberRepository, SessionManager sessionManager) {
        this.memberRepository = memberRepository;
        this.sessionManager = sessionManager;
    }

    public LoginResponse login(LoginRequest req) {
        MemberEntity member = memberRepository.findByLoginId(req.loginId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_USERNAME));

        if (!member.getPassword().equals(req.password())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = sessionManager.createSession(member.getId());
        return new LoginResponse(token, "Bearer");
    }

    public void logout(String token) {
        sessionManager.removeSession(token);
    }
}