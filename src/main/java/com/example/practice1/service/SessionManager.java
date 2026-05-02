package com.example.practice1.service;

import com.example.practice1.repository.member.MemberRepository;

public class SessionManager {
    private final MemberRepository memberRepository;

    public SessionManager(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String createSession(Long memberId){

        return "세션 생성 성공";
    }

    public Long getMemberId(String token){

        return null;
    }

    public void removeSession(String token){

    }

}
