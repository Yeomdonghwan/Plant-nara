package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname;
    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDate date;

    // ... other fields and methods

    @PrePersist//객체생성시 자동실행
    public void prePersist() {
        date = LocalDate.now();
    }

    public static ArticleEntity createArticleEntity(ArticleDto dto){
        return new ArticleEntity(
          null,
          dto.getNickname(),
          dto.getTitle(),
          dto.getContent(),
                null
        );
    }

}
