package com.example.PlantsCafe.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ArticleDto {

    private Long id;
    private String nickname;
    private String title;
    private String content;
    private String date;
}
