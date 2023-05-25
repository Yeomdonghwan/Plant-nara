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

    public void deleteArticle(Long articleId, User loggedInUser, ArticleDto articleDto) {
        //게시물이 존재하지 않는 경우
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("Article not found with id: " + articleId));

        if(article.getAuthor() != null){
            //익명글이 아닌경우

            //게시물 작성자와 로그인한 유저가 다른 경우
            if (!article.getAuthor().getId().equals(loggedInUser.getId())) {
                throw new IllegalStateException("This article does not belong to the logged-in user.");
            }
        }else{
            //익명글인 경우
            if(!articleDto.getPassword().equals(article.getAnonymous_password())){
                throw new IllegalStateException("Password wrong");
            }

        }

        articleRepository.delete(article);
    }

    public ArticleDto getArticleDtoForEdit(Long articleId, User loggedInUser, String password) {
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "수정할 수 없습니다"));
        if(article.getAuthor()==null){
            //익명글인 경우 패스워드를 확인
            if(!password.equals(article.getAnonymous_password())){
                throw new IllegalStateException("Password wrong");
            }
        }else {
            //로그인한 유저라면 본인의 글인지 확인
            if (!article.getAuthor().getId().equals(loggedInUser.getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정할 수 없습니다");
            }
        }
        return ArticleDto.createArticleDto(article);
    }

    public void editArticle(Long articleId,ArticleDto articleDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalStateException("not exist"));

        article.setArticle(articleDto);
    }
}
