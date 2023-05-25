package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.Article;
import com.example.PlantsCafe.Entity.Comment;
import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.CommentDto;
import com.example.PlantsCafe.repository.ArticleRepository;
import com.example.PlantsCafe.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Transactional
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    public void createComment(User loggedInUser, Long articleId, CommentDto commentDto) {
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("게시물 없음"));

        Comment comment = commentDto.toComment(article,loggedInUser);
        commentRepository.save(comment);
    }

    public List<CommentDto> getCommentDtos(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        List<CommentDto> commentDtos = new ArrayList<>();
        for(Comment comment:comments){
            commentDtos.add(comment.toDto());
        }
        return commentDtos;
    }

    public void deleteComment(CommentDto commentDto, User loggedInUser) {
        log.info(commentDto.getPassword()+"this is password!+++++++++++++++++++");
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(
                ()->new IllegalStateException("댓글이 존재하지 않습니다.")
        );
        if(comment.getAuthor()!=null){
            //로그인 유저가 작성한 댓글인 경우
            if(loggedInUser!=comment.getAuthor())
                throw new IllegalStateException("본인이 작성한 댓글이 아닙니다.");
        }else{
            //익명 유저가 작성한 댓글인 경우
            if(!commentDto.getPassword().equals(comment.getAnonymous_password()))
                throw new IllegalStateException("비밀번호가 틀렸습니다.");
        }

        commentRepository.delete(comment);
    }
}
