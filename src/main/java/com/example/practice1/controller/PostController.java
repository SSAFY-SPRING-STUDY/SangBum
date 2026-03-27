package com.example.practice1.controller;


import com.example.practice1.dto.PostResponse;
import com.example.practice1.service.PostService;
import com.example.practice1.dto.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    //개시글 생성
    //Contoller 계층에서 유형성 검사도 같이 함.
    //계층별 역할
    @PostMapping("/api/posts")
    public String createPost(@RequestBody CreatePostRequest req){

        System.out.println(req);
        PostResponse response = postService.save(req);
        return "post";
    }



    @GetMapping("/api/posts")
    public List finAllPosts(){


        return null;
    }


    @GetMapping("/api/posts/{id}")
    public List findPostById(int id){


        return null;
    }


    @PutMapping("/api/posts/{id}")
    public List updatePost(int id){


        return null;
    }

    @DeleteMapping("/api/posts/{id}")
    public String deletePost(int id){
        return null;
    }





}
