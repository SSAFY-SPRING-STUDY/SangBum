package com.example.practice1.service;

import com.example.practice1.dto.login.LoginRequest;
import com.example.practice1.dto.login.LoginResponse;
import com.example.practice1.repository.member.MemberRepository;

public class AuthService {
    private final MemberRepository memberRepository;

    public AuthService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public LoginResponse login(LoginRequest req) {
        return null;
    }
    public void logout(){

    }
}
