package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.Article;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    Article save(Article article);
    Optional<Article> findById(Long id);
//    Optional<Article> findByNickname(String nickname);
    List<Article> findAll();

    void delete(Article article);
    void deleteById(Long id);
}
