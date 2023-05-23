package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
