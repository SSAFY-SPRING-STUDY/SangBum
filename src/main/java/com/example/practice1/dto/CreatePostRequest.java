package com.example.practice1.dto;

public class CreatePostRequest {

    public String title;
    public String content;
    public String Author;

    @Override
    public String toString() {
        return "CreatePostRequest{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", Author='" + Author + '\'' +
                '}';
    }
}
