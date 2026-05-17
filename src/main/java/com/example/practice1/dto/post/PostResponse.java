package com.example.practice1.dto.post;

import com.example.practice1.dto.member.MemberResponse;
import com.example.practice1.entity.PostEntity;

public record PostResponse(
        Long id,
        String title,
        String content,
        MemberResponse memberResponse
) {

    public static PostResponse from(PostEntity post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                MemberResponse.from(post.getAuthor())
        );
    }
}