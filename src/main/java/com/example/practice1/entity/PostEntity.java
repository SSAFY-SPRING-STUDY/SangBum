package com.example.practice1.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PostEntity {

    private Long id;
    private String title;
    private String content;
    private String author;



}
