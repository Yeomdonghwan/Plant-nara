package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.dto.UserDto;
import com.example.PlantsCafe.service.BackLoginService;
import com.example.PlantsCafe.repository.UserRepository;
//import com.example.PlantsCafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller @RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final BackLoginService userService;

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginPage";
    }

    @GetMapping("/signUp")
    public String signUpForm(){

        return "members/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserDto userDto){
        userService.addUser(userDto);
        return "redirect:/";
    }
}
