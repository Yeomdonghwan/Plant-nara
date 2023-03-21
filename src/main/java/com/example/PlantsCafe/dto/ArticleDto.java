package com.example.PlantsCafe.dto;


import com.example.PlantsCafe.Entity.ArticleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
@Setter
public class ArticleDto {

    private Long id;
    private String nickname;
    private String title;
    private String content;
    private LocalDate date;

    public static ArticleDto createArticleDto(ArticleEntity articleEntity){
        return new ArticleDto(
                articleEntity.getId(),
                articleEntity.getNickname(),
                articleEntity.getTitle(),
                articleEntity.getContent(),
                articleEntity.getDate()
        );
    }
}
