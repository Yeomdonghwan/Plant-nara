package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.ArticleDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@Entity
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User author;
//    private String nickname;
    @Column
    private String title;

    @Column
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Column
    private LocalDateTime createdAt;

    // ... other fields and methods

    private String anonymous_nickname; //익명사용자 필드
    private String anonymous_password;

    @PrePersist//객체생성시 자동실행
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public static Article createArticleEntity(User user,ArticleDto dto){
        if(user!=null){
        return new Article(
          null,
          user,
          dto.getTitle(),
          dto.getContent(),
                null,
                null,
                null,
                null
        );}
        else {
            return new Article(
                    null,
                    null,
                    dto.getTitle(),
                    dto.getContent(),
                    null,null,
                    dto.getNickname(),
                    dto.getPassword()
            );
        }
    }

    public void setArticle(ArticleDto articleDto){
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
    }

}
