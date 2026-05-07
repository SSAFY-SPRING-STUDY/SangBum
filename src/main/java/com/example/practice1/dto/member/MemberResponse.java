package com.example.practice1.dto.member;

import com.example.practice1.entity.MemberEntity;

public record MemberResponse(Long id, String loginId, String name) {
    public static MemberResponse from(MemberEntity member){
        return new MemberResponse(member.getId(), member.getLoginId(), member.getName());
    }

}
