package com.example.PlantsCafe.login;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 protected 접근지정
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    private String name;
    private String password;
    private String role;

    @Builder
    public UserEntity(String name, String password, String role){
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
