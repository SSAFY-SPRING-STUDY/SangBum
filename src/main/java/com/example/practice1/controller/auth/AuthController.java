package com.example.practice1.controller.auth;
import com.example.practice1.dto.login.LoginRequest;
import com.example.practice1.dto.login.LoginResponse;
import com.example.practice1.service.AuthService;
import com.example.practice1.service.SessionManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class AuthController {

    private final AuthService authService;
    private final SessionManager sessionManager;

    public AuthController(AuthService authService, SessionManager sessionManager) {
        this.authService = authService;
        this.sessionManager = sessionManager;
    }

    @PostMapping("/api/auth/login")
    public LoginResponse login(@RequestBody LoginRequest req){
        try{
            return authService.login(req);
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/api/auth/logout")
    public void logout(@RequestHeader(value="Authorization", required=false) String authorization){
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        String token = authorization.substring("Bearer ".length());


        Long memberId = sessionManager.getMemberId(token);

        if (memberId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        authService.logout(token);
    }
}
