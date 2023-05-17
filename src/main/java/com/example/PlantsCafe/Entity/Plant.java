package com.example.PlantsCafe.Entity;

import jakarta.persistence.*;

@Entity
public class Plant {
    @Id @GeneratedValue
    private Long id;
    @Column
    private String plant_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column
    private int watering_schedule;
}
