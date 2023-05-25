package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.CommentDto;
import com.example.PlantsCafe.service.CommentService;
import com.example.PlantsCafe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller @RequiredArgsConstructor @Slf4j
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    /**
     * 게시물과 댓글을 함께 렌더링해야 하기 때문에 조회하는 메소드는 ArticleController에 있음.
     * */


    //댓글작성
    @PostMapping("/forum/{articleId}/comment")
    public String createComment(@PathVariable Long articleId, @RequestBody CommentDto commentDto){
        User loggedInUser;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (UsernameNotFoundException e) {
            loggedInUser = null;
        }
        commentService.createComment(loggedInUser,articleId,commentDto);
        return "redirect:/forum/"+articleId;
    }

    //댓글삭제
    @PostMapping("forum/{articleId}/comment/{commentId}")
    public String deleteComment(@PathVariable Long articleId, @PathVariable Long commentId,@RequestBody CommentDto commentDto){
        //articleId와 commentId는 현재 사용되지 않고 있음.
        User loggedInUser;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (UsernameNotFoundException e) {
            loggedInUser = null;
        }
        commentService.deleteComment(commentDto,loggedInUser);
        return "redirect:/forum/"+articleId;
    }
}
