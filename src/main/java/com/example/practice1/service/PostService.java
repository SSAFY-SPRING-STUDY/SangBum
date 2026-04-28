package com.example.practice1.service;


import com.example.practice1.controller.dto.PostRequest;
import com.example.practice1.controller.dto.PostResponse;
import com.example.practice1.entity.PostEntity;
import com.example.practice1.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse save(PostRequest req) {
        PostEntity post = new PostEntity(
                req.getTitle(),
                req.getContent(),
                req.getAuthor()
        );
        PostEntity saved = postRepository.save(post);

        return new PostResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getAuthor()
        );
    }

// findAll
    public List<PostResponse> findAll() {
        List<PostEntity> posts = new ArrayList<>();
        posts = postRepository.findAll();
        List<PostResponse> responses = new ArrayList<>();
        for(PostEntity post : posts) {
            PostResponse postResp = new PostResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getAuthor()
            );
            responses.add(postResp);
        }

        return responses;
    }


    public PostResponse findById(Long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("찾는 게시글이 없습니다."));
        PostResponse postResp = new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor()
        );
        return postResp;
    }

    public void update(Long id, PostRequest req) {
        PostEntity post = postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("게시물이 없습니다."));
        post.update(req.getTitle(), req.getContent(), req.getAuthor());
    }

    public void delete(Long id){

        postRepository.findById(id).orElseThrow(()->new IllegalArgumentException());
        postRepository.deleteById(id);
    }



}
