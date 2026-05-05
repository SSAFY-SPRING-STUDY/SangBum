package com.example.practice1.service;

import com.example.practice1.dto.login.LoginRequest;
import com.example.practice1.dto.login.LoginResponse;
import com.example.practice1.entity.MemberEntity;
import com.example.practice1.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private SessionManager sessionManager;

    public AuthService(MemberRepository memberRepository, SessionManager sessionManager) {
        this.memberRepository = memberRepository;
        this.sessionManager = sessionManager;
    }


    public LoginResponse login(LoginRequest req) {
        MemberEntity member = memberRepository.findByLoginId(req.loginId())
                .orElseThrow(()-> new IllegalArgumentException("아이디 똔느 비밀번호가 올바르지 않습니다."));

        if(!member.getPassword().equals(req.password())) {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }

        String token = sessionManager.createSession(member.getId());

        return new LoginResponse(token,"Bearer");
    }
    public void logout(String token){
        sessionManager.removeSession(token);
    }
}
