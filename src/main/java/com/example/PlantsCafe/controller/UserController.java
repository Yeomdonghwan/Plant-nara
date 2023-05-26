package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.dto.UserDto;
import com.example.PlantsCafe.service.BackLoginService;
import com.example.PlantsCafe.repository.UserRepository;
//import com.example.PlantsCafe.service.UserService;
import com.example.PlantsCafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller @RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginForm(){
        return "loginPage";
    }

    @GetMapping("/signUp")
    public String signUpForm(){

        return "members/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return "redirect:/";
    }

    @GetMapping("/currentUser")
    @ResponseBody
    public Object getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();  // 현재 로그인한 사용자의 이름을 반환합니다.
    }
}
