package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.PlantDto;
import com.example.PlantsCafe.service.PlantService;
import com.example.PlantsCafe.service.UserService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PlantController {

    private final PlantService plantService;
    private final UserService userService;
    @GetMapping("/plant")
    public String plantList(Model model){
        User loggedInUser = userService.getLoggedInUser();
        List<PlantDto> plants = plantService.getPlantList(loggedInUser);

        model.addAttribute("plants",plants);
        return "plant/list";
    }

    @PostMapping("plant/create")
    public String createPlant(@RequestBody PlantDto plantDto){
        User loggedInUser = userService.getLoggedInUser();
        plantService.addPlant(plantDto,loggedInUser);

        return "redirect:/plant";
    }

    @PostMapping("plant/edit")
    public String editPlant(@RequestBody PlantDto plantDto){
        User loggedInUser = userService.getLoggedInUser();
        plantService.editPlant(plantDto,loggedInUser);

        return "redirect:/plant";
    }

    @PostMapping("plant/{plantId}/watering")
    public String watering(@PathVariable Long plantId){
        User loggedInUser = userService.getLoggedInUser();
        plantService.watering(plantId,loggedInUser);
        return "redirect:/plant";
    }

    @PostMapping("plant/{plantId}/delete")
    public String deletePlant(@PathVariable Long plantId){
        User loggedInUser = userService.getLoggedInUser();
        plantService.deletePlant(plantId,loggedInUser);
        return "redirect:/plant";
    }
}
