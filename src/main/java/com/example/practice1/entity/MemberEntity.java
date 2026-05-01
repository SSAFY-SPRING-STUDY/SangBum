package com.example.practice1.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberEntity {

    private Long id;
    private String loginId;
    private String password;
    private String name;

    public void assignId(Long id){
        this.id = id;
    }


}
