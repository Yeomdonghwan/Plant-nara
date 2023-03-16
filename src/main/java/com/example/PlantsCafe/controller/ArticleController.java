package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.Entity.ArticleEntity;
import com.example.PlantsCafe.dto.ArticleDto;
import com.example.PlantsCafe.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/forum")
    public String forum(Model model){
        List<ArticleEntity> articles = (List<ArticleEntity>) articleRepository.findAll();
        model.addAttribute("articles",articles);

        return "articles/list";
    }

    @GetMapping("/forum/new")
    public String newArticle(){
        return "articles/new";
    }

    @PostMapping("/forum/create")
    public String createArticle(ArticleDto dto){
        log.info(dto.toString());
        ArticleEntity entity = ArticleEntity.createArticleEntity(dto);
        log.info("entity=>{}",entity.toString());
        ArticleEntity saved = articleRepository.save(entity);
        log.info("save=>{}",saved);
        return "redirect:/forum";
    }
}
