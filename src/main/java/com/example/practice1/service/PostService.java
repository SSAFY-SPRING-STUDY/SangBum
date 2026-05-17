package com.example.practice1.service;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.dto.post.PostRequest;
import com.example.practice1.dto.post.PostResponse;
import com.example.practice1.entity.MemberEntity;
import com.example.practice1.entity.PostEntity;
import com.example.practice1.repository.member.MemberRepository;
import com.example.practice1.repository.post.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public PostResponse create(PostRequest request, Long authorId) {
        MemberEntity author = findMember(authorId);
        PostEntity post = request.toEntity(author);
        PostEntity saved = postRepository.save(post);

        return PostResponse.from(saved);
    }

    public List<PostResponse> findAll() {
        return postRepository.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();
    }

    public PostResponse getPostById(Long id) {
        PostEntity post = findPost(id);
        return PostResponse.from(post);
    }

    public PostResponse update(PostRequest request, Long id, Long authorId) {
        MemberEntity author = findMember(authorId);
        PostEntity post = findPost(id);
        validateAuthor(post, author);

        post.update(request.title(), request.content());
        return PostResponse.from(post);
    }

    public void delete(Long id, Long authorId) {
        MemberEntity author = findMember(authorId);
        PostEntity post = findPost(id);
        validateAuthor(post, author);

        postRepository.delete(post);
    }

    private MemberEntity findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private PostEntity findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
    }

    private void validateAuthor(PostEntity post, MemberEntity author) {
        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new CustomException(ErrorCode.INVALID_PERMISSION);
        }
    }
}