package com.example.PlantsCafe.Entity;

import com.example.PlantsCafe.dto.PlantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Entity @AllArgsConstructor @NoArgsConstructor @Getter
public class Plant {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String species;
    @Column
    private String nickname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;
    @Column
    private int wateringFrequency;
    @Column
    private LocalDate lastWateredDate;
    @Column
    private LocalDate plantingDate;
    @Column
    private String notes;

    @Column
    private LocalDate nextWateringDate;

    @Column
    private PlantStatus status; //식물의 상태[NEEDS_WATER/NORMAL]
    public PlantDto toDto(){
        return new PlantDto(
                this.id,
                this.species,
                this.nickname,
                this.plantingDate,
                this.wateringFrequency,
                this.lastWateredDate,
                this.nextWateringDate,
                this.notes,
                this.status
        );
    }

    public void calculateNextWateringDate() {
        this.nextWateringDate = lastWateredDate.plusDays(wateringFrequency);
    }


    @Scheduled(cron = "0 0 0 * * *") //매일 자정에 실행됨.
    public void calculateStatus() {
        if (nextWateringDate.isBefore(LocalDate.now())) {
            status = PlantStatus.NEEDS_WATER; // 물을 줘야 할 시간 지남
        } else {
            status = PlantStatus.NORMAL;
        }
    }

    public void setFromDto(PlantDto dto){
        this.wateringFrequency = dto.getWateringFrequency();
        this.species = dto.getSpecies();
        this.nickname = dto.getNickname();
        this.lastWateredDate = dto.getLastWateredDate();
        this.notes = dto.getNotes();
        this.plantingDate = dto.getPlantingDate();
    }

    public void watering(){
        this.lastWateredDate = LocalDate.now();
        calculateNextWateringDate();
        calculateStatus();
    }

}
