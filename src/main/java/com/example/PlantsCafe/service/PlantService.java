package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.Plant;
import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.PlantDto;
import com.example.PlantsCafe.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Transactional
@RequiredArgsConstructor
public class PlantService {
    private final PlantRepository plantRepository;

    public void addPlant(PlantDto plantDto, User loggedInUser){
        Plant entity = plantDto.toEntity(loggedInUser);
        entity.calculateNextWateringDate();
        entity.calculateStatus();
        plantRepository.save(entity);
        return;
    }

    public List<PlantDto> getPlantList(User loggedInUser) {
        List<Plant> plantEntityList = plantRepository.findByOwner(loggedInUser);
        List<PlantDto> plantDtoList = new ArrayList<>();

        for (Plant plant : plantEntityList) {
            PlantDto plantDto = plant.toDto();
            plantDtoList.add(plantDto);
        }

        return plantDtoList;

    }

    public void editPlant(PlantDto plantDto, User loggedInUser) {
        Plant entity = plantRepository.findById(plantDto.getId()).orElseThrow( () -> new IllegalArgumentException("존재하지 않는 식물"));

        if(entity.getOwner().getId() != loggedInUser.getId()){
            throw new IllegalStateException("수정 권한이 없습니다.");
        }

        entity.setFromDto(plantDto);
        entity.calculateNextWateringDate();
        entity.calculateStatus();


    }

    public void watering(Long plantId, User loggedInUser) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 식물입니다."));
        if(plant.getOwner().getId() != loggedInUser.getId()){
            throw new IllegalStateException("본인의 식물이 아닙니다.");
        }

        plant.watering();
    }

    public void deletePlant(Long plantId, User loggedInUser) {
        Plant plant = plantRepository.findById(plantId).orElseThrow(()->new IllegalArgumentException("존재하지 않는 식물입니다."));
        if(plant.getOwner().getId() != loggedInUser.getId()){
            throw new IllegalStateException("본인의 식물이 아닙니다.");
        }
        plantRepository.deleteById(plantId);
    }
}
