package com.example.practice1.service;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.dto.member.MemberRequest;
import com.example.practice1.dto.member.MemberResponse;
import com.example.practice1.entity.MemberEntity;
import com.example.practice1.repository.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponse save(MemberRequest req) {
        MemberEntity member = new MemberEntity(req.loginId(), req.password(), req.name());
        MemberEntity saved = memberRepository.save(member);

        return MemberResponse.from(saved);
    }

    public MemberResponse findById(Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberResponse.from(member);
    }
}