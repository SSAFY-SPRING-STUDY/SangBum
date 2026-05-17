package com.example.practice1.service;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.dto.member.MemberRequest;
import com.example.practice1.dto.member.MemberResponse;
import com.example.practice1.entity.MemberEntity;
import com.example.practice1.repository.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse save(MemberRequest req) {
        MemberEntity member = MemberEntity.create(
                req.loginId(),
                req.password(),
                req.name()
        );

        MemberEntity savedMember = memberRepository.save(member);

        return MemberResponse.from(savedMember);
    }

    @Transactional(readOnly = true)
    public MemberResponse findById(Long id) {
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberResponse.from(member);
    }
}