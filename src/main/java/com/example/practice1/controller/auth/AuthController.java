package com.example.practice1.controller.auth;

import com.example.practice1.common.exception.CustomException;
import com.example.practice1.common.exception.ErrorCode;
import com.example.practice1.common.response.ApiResponse;
import com.example.practice1.dto.login.LoginRequest;
import com.example.practice1.dto.login.LoginResponse;
import com.example.practice1.service.AuthService;
import com.example.practice1.service.SessionManager;
import com.example.practice1.util.AuthTokenUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;
    private final SessionManager sessionManager;

    public AuthController(AuthService authService, SessionManager sessionManager) {
        this.authService = authService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest req) {
        LoginResponse response = authService.login(req);

        return ResponseEntity.ok(ApiResponse.success("로그인 성공", response));
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "Authorization", required = false) String bearerToken
    ) {
        String sessionKey = extractSessionKey(bearerToken);
        authService.logout(sessionKey);

        return ResponseEntity.noContent().build();
    }

    private String extractSessionKey(String bearerToken) {
        if (!AuthTokenUtils.isValidBearerToken(bearerToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String sessionKey = AuthTokenUtils.parseBearerToken(bearerToken);
        Long memberId = sessionManager.getMemberId(sessionKey);

        if (memberId == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        return sessionKey;
    }
}