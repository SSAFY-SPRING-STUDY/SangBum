package com.example.practice1.controller.member;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.common.response.ApiResponse;
import com.example.practice1.dto.member.MemberRequest;
import com.example.practice1.dto.member.MemberResponse;
import com.example.practice1.service.MemberService;
import com.example.practice1.service.SessionManager;
import com.example.practice1.util.AuthTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    public MemberController(MemberService memberService, SessionManager sessionManager) {
        this.memberService = memberService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/api/members")
    public ResponseEntity<ApiResponse<MemberResponse>> join(@RequestBody MemberRequest req) {
        MemberResponse response = memberService.save(req);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("회원가입 성공", response));
    }

    @GetMapping("/api/members/me")
    public ResponseEntity<ApiResponse<MemberResponse>> me(
            @RequestHeader(value = "Authorization", required = false) String bearerToken
    ) {
        Long memberId = extractMemberId(bearerToken);
        MemberResponse response = memberService.findById(memberId);

        return ResponseEntity.ok(ApiResponse.success("내 정보 조회 성공", response));
    }

    private Long extractMemberId(String bearerToken) {
        if (!AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);

        if (memberId == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return memberId;
    }
}