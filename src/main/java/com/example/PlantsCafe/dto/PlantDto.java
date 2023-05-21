package com.example.PlantsCafe.dto;

import com.example.PlantsCafe.Entity.Plant;
import com.example.PlantsCafe.Entity.PlantStatus;
import com.example.PlantsCafe.Entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantDto {

    private Long id;
    private String species;
    private String nickname;
    private LocalDate plantingDate;
    private int wateringFrequency;
    private LocalDate lastWateredDate;

    private LocalDate nextWateringDate;
    private String notes;

    private PlantStatus status;

    public Plant toEntity(User loggedInUser){
        return new Plant(
                null,
                this.species,
                this.nickname,
                loggedInUser,
                this.wateringFrequency,
                this.lastWateredDate,
                this.plantingDate,
                this.notes,
                this.nextWateringDate,
                this.status
        );
    }


}
