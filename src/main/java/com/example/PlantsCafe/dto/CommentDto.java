package com.example.PlantsCafe.dto;

import com.example.PlantsCafe.Entity.Article;
import com.example.PlantsCafe.Entity.Comment;
import com.example.PlantsCafe.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CommentDto {

    Long id;
    String nickname;
    String password;
    String content;
    LocalDateTime createdAt;

    Boolean isAnonymous;
    @Override
    public String toString() {
        return id+": nickname:"+nickname+", password: "+password+", content: "+content+", createdAt: "+createdAt;
    }

    public Comment toComment(Article article,User loggedInUser){ //로그인유저라면 author에 로그인유저정보 저장, 아니라면 익명닉네임과 비밀번호 저장
        User author = (loggedInUser!=null)?loggedInUser:null;
        String anonymousPassword = (loggedInUser!=null)?null:password;
        return new Comment(
                null,
                author,
                article,
                content,
                null,
                nickname,
                anonymousPassword

        );
    }
}
