package com.example.practice1.controller.member;

import com.example.practice1.dto.member.MemberRequest;
import com.example.practice1.dto.member.MemberResponse;
import com.example.practice1.service.MemberService;
import com.example.practice1.service.SessionManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class MemberController {

    private final MemberService memberService;
    private final SessionManager sessionManager;

    public MemberController(MemberService memberService, SessionManager sessionManager) {
        this.memberService = memberService;
        this.sessionManager = sessionManager;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/members")
    public MemberResponse join(@RequestBody MemberRequest req){
        memberService.save(req);
        return new MemberResponse(req.loginId(),req.password(),req.name());
    }

    @GetMapping("/api/members/me")
    public MemberResponse me(@RequestHeader(value = "Authorization", required = false) String authorization){
        String token = authorization.substring("Bearer ".length());
        Long memberId = sessionManager.getMemberId(token);
        try{
            return   memberService.findById(memberId);
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
}
