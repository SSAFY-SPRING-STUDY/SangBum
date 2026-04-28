package com.example.practice1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    private Long id;
    private String title;
    private String content;
    private String author;


    public PostEntity(String title, String content, String author) {
    }
    public void update(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
