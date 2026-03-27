package com.example.practice1.service;


import com.example.practice1.controller.dto.CreatePostRequest;
import com.example.practice1.controller.dto.PostResponse;
import com.example.practice1.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostResponse save(CreatePostRequest req) {
        postRepository.save();

    }
}
