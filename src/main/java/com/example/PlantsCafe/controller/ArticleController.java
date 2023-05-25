package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.Entity.Article;
import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.ArticleDto;
import com.example.PlantsCafe.dto.CommentDto;
import com.example.PlantsCafe.dto.UserDto;
import com.example.PlantsCafe.service.ArticleService;
import com.example.PlantsCafe.service.CommentService;
import com.example.PlantsCafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping("/forum")
    public String forum(Model model){
        List<Article> articles = articleService.findArticles();
        List<ArticleDto> articleDtos = new ArrayList<>();
        for (Article article : articles) {
            ArticleDto articleDto = ArticleDto.createArticleDto(article);
            articleDtos.add(articleDto);
        }


        model.addAttribute("articles",articleDtos);

        return "articles/list";
    }

    @GetMapping("/forum/new")
    public String createArticleForm(Model model){

        User loggedInUser;
        UserDto userDto;
        try {
            loggedInUser = userService.getLoggedInUser();
            userDto=loggedInUser.toDto();
        } catch (UsernameNotFoundException e) {
            userDto = null;
        }

        model.addAttribute("user",userDto);


//        String nickname = userService.getLoggedInUser().getNickname();
//        // 뷰로 nickname 값을 전달
//        model.addAttribute("nickname", nickname);
        return "articles/new";
    }

    @PostMapping("/forum/create")
    public String createArticle(ArticleDto dto){
//        User loggedInUser = userService.getLoggedInUser();
//        articleService.createArticle(dto,loggedInUser);

        User loggedInUser;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (UsernameNotFoundException e) {
            loggedInUser = null;
        }

        articleService.createArticle(dto,loggedInUser);


        return "redirect:/forum";
    }

    @GetMapping("/forum/{articleId}")
    public String articleDetail(@PathVariable Long articleId, Model model){
        ArticleDto articleDto = articleService.articleDetail(articleId);

        User loggedInUser;
        UserDto userDto;
        try {
            loggedInUser = userService.getLoggedInUser();
            userDto=loggedInUser.toDto();
        } catch (UsernameNotFoundException e) {
           userDto = null;
        }

        List<CommentDto> commentDtos = commentService.getCommentDtos(articleId);

        model.addAttribute("comments",commentDtos);
        model.addAttribute("article",articleDto);
        model.addAttribute("user",userDto);
        return "articles/show";
    }

    @PostMapping("/forum/{articleId}/delete")
    public String deleteArticle(@PathVariable Long articleId, @RequestBody ArticleDto articleDto){
//        User loggedInUser = userService.getLoggedInUser();
        User loggedInUser;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (UsernameNotFoundException e) {
            loggedInUser = null;
        }


        articleService.deleteArticle(articleId,loggedInUser,articleDto);
        return "redirect:/forum";//이 리다이렉트가 적용되지 않는 문제가 있어 articles/show의 script에서 리다이렉션 하도록 하였음
    }

    @GetMapping("/forum/{articleId}/edit")
    public String editForm(@PathVariable Long articleId, Model model,@RequestParam(value = "password", required = false) String password){
        User loggedInUser;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (UsernameNotFoundException e) {
            loggedInUser = null;
        }
        ArticleDto articleDto = articleService.getArticleDtoForEdit(articleId, loggedInUser,password);
        model.addAttribute("article", articleDto);
        return "articles/edit";
    }

    @PostMapping("/forum/{articleId}/edit")
    public String editArticle(@PathVariable Long articleId,ArticleDto articleDto){
        articleService.editArticle(articleId,articleDto);
        return "redirect:/forum/{articleId}";
    }
}
