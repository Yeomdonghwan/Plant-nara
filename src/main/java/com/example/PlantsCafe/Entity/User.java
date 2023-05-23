package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.UserDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //기본생성자 protected 접근지정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String password;
    private String role;

    private String nickname;
    @Builder
    public User(String name, String password, String role, String nickname){
        this.name = name;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
    }

    public UserDto toDto(){
        return new UserDto(null,null,nickname);
    }
}
