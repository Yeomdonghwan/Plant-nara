package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity,Long> {

}
