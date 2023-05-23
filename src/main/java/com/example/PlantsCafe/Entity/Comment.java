package com.example.PlantsCafe.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity @Getter
public class Comment {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    private String content;
    private LocalDateTime createdAt;

    @PrePersist//객체생성시 자동실행
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }



}
