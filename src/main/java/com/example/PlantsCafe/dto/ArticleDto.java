package com.example.PlantsCafe.dto;


import com.example.PlantsCafe.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
@Setter
public class ArticleDto {

    private Long id;
    private String nickname;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    private String password;

    private Boolean isAnonymous;

    public static ArticleDto createArticleDto(Article article){
        if(article.getAuthor() != null){ //익명글 아닌경우
            return new ArticleDto(
                    article.getId(),
                    article.getAuthor().getNickname(),
                    article.getTitle(),
                    article.getContent(),
                    article.getCreatedAt(),
                    null,
                    false
            );
        }else{
            return new ArticleDto(
                    article.getId(),
                    article.getAnonymous_nickname()+"(익명)",
                    article.getTitle(),
                    article.getContent(),
                    article.getCreatedAt(),
                    null,
                    true
            );
        }

    }
}
