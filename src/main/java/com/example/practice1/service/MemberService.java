package com.example.practice1.service;

import com.example.practice1.dto.member.MemberRequest;
import com.example.practice1.dto.member.MemberResponse;
import com.example.practice1.repository.member.MemberRepository;

public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public MemberResponse save(MemberRequest req){


        return null;
    }

    public MemberResponse findById(String id){

        return null;
    }
}
