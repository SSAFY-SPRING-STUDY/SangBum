package com.example.practice1.controller.post;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.common.response.ApiResponse;
import com.example.practice1.dto.post.PostRequest;
import com.example.practice1.dto.post.PostResponse;
import com.example.practice1.service.PostService;
import com.example.practice1.service.SessionManager;
import com.example.practice1.util.AuthTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;
    private final SessionManager sessionManager;

    public PostController(PostService postService, SessionManager sessionManager) {
        this.postService = postService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/api/posts")
    public ResponseEntity<ApiResponse<PostResponse>> createPost(
            @RequestHeader(value = "Authorization", required = false) String bearerToken,
            @RequestBody PostRequest request
    ) {
        Long authorId = extractAuthorId(bearerToken);
        PostResponse response = postService.create(request, authorId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("게시글 생성 성공", response));
    }

    @GetMapping("/api/posts")
    public ResponseEntity<ApiResponse<List<PostResponse>>> findAllPosts() {
        List<PostResponse> response = postService.findAll();

        return ResponseEntity.ok(ApiResponse.success("게시글 목록 조회 성공", response));
    }

    @GetMapping("/api/posts/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> findPostById(@PathVariable Long id) {
        PostResponse response = postService.getPostById(id);

        return ResponseEntity.ok(ApiResponse.success("게시글 조회 성공", response));
    }

    @PutMapping("/api/posts/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String bearerToken,
            @RequestBody PostRequest request
    ) {
        Long authorId = extractAuthorId(bearerToken);
        PostResponse response = postService.update(request, id, authorId);

        return ResponseEntity.ok(ApiResponse.success("게시글 수정 성공", response));
    }

    @DeleteMapping("/api/posts/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @RequestHeader(value = "Authorization", required = false) String bearerToken
    ) {
        Long authorId = extractAuthorId(bearerToken);
        postService.delete(id, authorId);

        return ResponseEntity.noContent().build();
    }

    private Long extractAuthorId(String bearerToken) {
        if (!AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long authorId = sessionManager.getMemberId(sessionKey);

        if (authorId == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return authorId;
    }
}