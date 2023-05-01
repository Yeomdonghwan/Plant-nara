package com.example.PlantsCafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlantController {

    @GetMapping("/plant")
    public String plantList(){
        return "plant/list";
    }
}
