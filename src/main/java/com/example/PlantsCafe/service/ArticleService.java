package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.ArticleEntity;
import com.example.PlantsCafe.dto.ArticleDto;
import com.example.PlantsCafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    ArticleRepository articleRepository;

    @Autowired //DI
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }


    public List<ArticleEntity> findArticles() {
        return articleRepository.findAll();
    }

    public void createArticle(ArticleDto dto) {
//        log.info(dto.toString());
        ArticleEntity entity = ArticleEntity.createArticleEntity(dto);
//        log.info("entity=>{}",entity.toString());
        ArticleEntity saved = articleRepository.save(entity);
//        log.info("save=>{}",saved);
    }

    public ArticleDto articleDetail(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id).orElse(null);
        return ArticleDto.createArticleDto(articleEntity);
    }
}
