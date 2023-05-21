package com.example.PlantsCafe.repository;

import com.example.PlantsCafe.Entity.Plant;
import com.example.PlantsCafe.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    Plant save(Plant plant);
    Optional<Plant> findById(Long id);

    List<Plant> findByOwner(User owner);

    //    Optional<Article> findByNickname(String nickname);
    List<Plant> findAll();
    void deleteById(Long id);
}
