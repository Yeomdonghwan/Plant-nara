package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginPage";
    }

    @GetMapping("/signUp")
    public String signUp(){
//        UserEntity userEntity = UserEntity.builder()
//                .name("testuser")
//                .password(passwordEncoder.encode("1234"))
//                .role("user")
//                .build();
//
//        userRepository.save(userEntity);

//        return "redirect:/login";
        return "members/signUp";
    }
}
