package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.ArticleDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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
    private User user;
//    private String nickname;
    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDate date;

    // ... other fields and methods

    @PrePersist//객체생성시 자동실행
    public void prePersist() {
        date = LocalDate.now();
    }

    public static Article createArticleEntity(User user,ArticleDto dto){
        return new Article(
          null,
          user,
          dto.getTitle(),
          dto.getContent(),
                null
        );
    }

}
