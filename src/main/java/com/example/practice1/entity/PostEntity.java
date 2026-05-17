package com.example.practice1.entity;

public class PostEntity {

    private Long id;
    private String title;
    private String content;
    private MemberEntity author;

    protected PostEntity() {
    }

    private PostEntity(String title, String content, MemberEntity author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public static PostEntity create(String title, String content, MemberEntity author) {
        return new PostEntity(title, content, author);
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public MemberEntity getAuthor() {
        return author;
    }
}