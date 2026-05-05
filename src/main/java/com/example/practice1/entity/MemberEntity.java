package com.example.practice1.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberEntity {

    private Long id;
    private String loginId;
    private String password;
    private String name;

    public MemberEntity(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }


    public void assignId(Long id){
        this.id = id;
    }


}
