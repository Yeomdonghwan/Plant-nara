package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.Entity.ArticleEntity;
import com.example.PlantsCafe.dto.ArticleDto;
import com.example.PlantsCafe.repository.ArticleRepository;
import com.example.PlantsCafe.service.ArticleService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {

    private ArticleService articleService;

    @Autowired //DI
    public ArticleController(ArticleService articleService){
        this.articleService=articleService;
    }
    @GetMapping("/")
    public String home(Model model, HttpSession session){

        Object greeting = session.getAttribute("greeting");
        if(greeting!=null)
            model.addAttribute("greeting",session.getAttribute("greeting"));
        return "home";
    }

    @GetMapping("/forum")
    public String forum(Model model){
        List<ArticleEntity> articles = articleService.findArticles();

//        List<ArticleEntity> articles = (List<ArticleEntity>) articleRepository.findAll();
        model.addAttribute("articles",articles);

        return "articles/list";
    }

    @GetMapping("/forum/new")
    public String newArticle(){
        return "articles/new";
    }

    @PostMapping("/forum/create")
    public String createArticle(ArticleDto dto){
        articleService.createArticle(dto);


        return "redirect:/forum";
    }

    @GetMapping("/forum/{id}")
    public String articleDetail(@PathVariable Long id, Model model){
        ArticleDto articleDto = articleService.articleDetail(id);

//        ArticleEntity articleEntity = articleRepository.findById(id).orElse(null);
//        ArticleDto articleDto = ArticleDto.createArticleDto(articleEntity);
        model.addAttribute("article",articleDto);
        return "articles/show";
    }
}
