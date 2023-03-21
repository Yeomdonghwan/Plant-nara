package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringDataJpaMemberRepository extends JpaRepository<MemberEntity,Long>,MemberRepository {
    @Override
    Optional<MemberEntity> findByUserId(String userId);
}
