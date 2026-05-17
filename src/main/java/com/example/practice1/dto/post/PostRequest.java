package com.example.practice1.dto.post;

import com.example.practice1.entity.MemberEntity;
import com.example.practice1.entity.PostEntity;

public record PostRequest(String title, String content) {

    public PostEntity toEntity(MemberEntity author) {
        return PostEntity.create(title, content, author);
    }
}