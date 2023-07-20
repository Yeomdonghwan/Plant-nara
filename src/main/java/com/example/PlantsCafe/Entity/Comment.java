package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Getter @AllArgsConstructor @NoArgsConstructor
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "article_id")
    private Article article;

    private String content;
    private LocalDateTime createdAt;

    private String anonymous_nickname;
    private String anonymous_password;
    @PrePersist//객체생성시 자동실행
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public CommentDto toDto(){
        return new CommentDto(
                id,
                (author!=null)? author.getNickname() : anonymous_nickname+"(익명)",
                null,
                content,
                createdAt,
                (author!=null)?false : true
        );
    }

    public void setFromDto(CommentDto dto){

    }

}
