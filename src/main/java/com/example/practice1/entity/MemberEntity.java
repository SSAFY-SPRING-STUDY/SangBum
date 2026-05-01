package com.example.practice1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberEntity {

    private Long id;
    private String loginId;
    private String password;
    private String name;


}
