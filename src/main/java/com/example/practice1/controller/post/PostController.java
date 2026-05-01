package com.example.practice1.controller.post;


import com.example.practice1.dto.post.PostRequest;
import com.example.practice1.dto.post.PostResponse;
import com.example.practice1.service.PostService;
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
    //Controller 계층에서 유형성 검사도 같이 함.
    //계층별 역할
    @PostMapping("/api/posts")
    public String createPost(@RequestBody PostRequest req){

        System.out.println(req);
        PostResponse response = postService.save(req);
        return "post";
    }

    @GetMapping("/api/posts")
    public List finAllPosts(){
        return postService.findAll();
    }

    @GetMapping("/api/posts/{id}")
    public PostResponse findPostById(@PathVariable Long id){
        return postService.findById(id);
    }


    @PutMapping("/api/posts/{id}")
    public String updatePost(@PathVariable Long id, @RequestBody PostRequest req){
        postService.update(id, req);
        return "수정 성공";
    }

    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id){
        postService.delete(id);
        return "삭제 성공";
    }





}
