package com.example.practice1.repository.member;

import com.example.practice1.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository {

    private static Map<Long, MemberEntity> memberStore = new ConcurrentHashMap<>();

    private static long sequence = 0L;



    public MemberEntity save(MemberEntity member){
        member.assignId(++sequence);
        memberStore.put(member.getId(), member);
        return member;
    };

    public Optional<MemberEntity> findByLoginId(String loginId){
        return memberStore.values().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }
    public Optional<MemberEntity> findById(Long id){
            return Optional.ofNullable(memberStore.get(id));
    };

}
