package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataJpaArticleRepository extends JpaRepository<ArticleEntity,Long>,ArticleRepository {
    @Override
    Optional<ArticleEntity> findByNickname(String nickname);
}
