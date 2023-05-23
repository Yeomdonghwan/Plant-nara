package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.service.CommentService;
import com.example.PlantsCafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller @RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    /**
     * 게시물과 댓글을 함께 렌더링해야 하기 때문에 조회하는 메소드는 ArticleController에 있음.
     * */


    //댓글작성
    @PostMapping("/forum/{articleId}/comment")
    public String createComment(@PathVariable Long articleId){

        return "redirect:/forum/"+articleId;
    }
}
