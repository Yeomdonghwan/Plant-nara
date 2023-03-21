package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    ArticleEntity save(ArticleEntity articleEntity);
    Optional<ArticleEntity> findById(Long id);
    Optional<ArticleEntity> findByNickname(String nickname);
    List<ArticleEntity> findAll();
}
