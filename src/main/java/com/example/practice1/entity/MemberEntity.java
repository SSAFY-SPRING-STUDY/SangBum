package com.example.practice1.entity;

public class MemberEntity {

    private Long id;
    private String loginId;
    private String password;
    private String name;

    protected MemberEntity() {
    }

    public MemberEntity(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}