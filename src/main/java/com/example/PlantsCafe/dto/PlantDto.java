package com.example.PlantsCafe.dto;

import com.example.PlantsCafe.Entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlantDto {

    Long id;
    String plant_name;
    int watering_schedule;
}
