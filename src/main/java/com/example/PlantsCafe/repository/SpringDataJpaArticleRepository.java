package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataJpaArticleRepository extends JpaRepository<Article,Long>,ArticleRepository {

    @Override
    Article save(Article article);
    @Override
    Optional<Article> findById(Long id);
    //    Optional<Article> findByNickname(String nickname);
    @Override
    List<Article> findAll();

    @Override
    void deleteById(Long id);

    @Override
    default void delete(Article article) {
        deleteById(article.getId());
    }


}
