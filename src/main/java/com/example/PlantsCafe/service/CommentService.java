package com.example.PlantsCafe.service;

import com.example.PlantsCafe.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
}
