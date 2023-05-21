package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.Article;
import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.ArticleDto;
import com.example.PlantsCafe.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service @Transactional
public class ArticleService {
    ArticleRepository articleRepository;

    @Autowired //DI
    public ArticleService(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }


    public List<Article> findArticles() {
        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public void createArticle(ArticleDto dto, User user) {
        Article entity = Article.createArticleEntity(user, dto);
        Article saved = articleRepository.save(entity);
    }

    public ArticleDto articleDetail(Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        return ArticleDto.createArticleDto(article);
    }

    public void deleteArticle(Long articleId, User loggedInUser) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + articleId));

        if (!article.getUser().getId().equals(loggedInUser.getId())) {
            throw new IllegalStateException("This article does not belong to the logged-in user.");
        }
        articleRepository.delete(article);
    }

    public ArticleDto getArticleDtoForEdit(Long articleId, User loggedInUser) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "수정할 수 없습니다"));
        if (!article.getUser().getId().equals(loggedInUser.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정할 수 없습니다");
        }
        return ArticleDto.createArticleDto(article);
    }

    public void editArticle(Long articleId,ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalStateException("not exist"));

        article.setArticle(articleDto);
    }
}
