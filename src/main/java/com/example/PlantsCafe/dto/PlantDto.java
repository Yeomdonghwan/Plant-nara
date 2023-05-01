package com.example.PlantsCafe.dto;

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
