package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.ArticleEntity;
import com.example.PlantsCafe.Entity.MemberEntity;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Optional<MemberEntity> findByUserId(String userId);

    MemberEntity save(MemberEntity memberEntity);
    Optional<MemberEntity> findById(Long id);
    List<MemberEntity> findAll();
}
