package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller @RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    @GetMapping("/")
    public String home(Model model, HttpSession session){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        boolean isAuthenticated = authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
//        model.addAttribute("isAuthenticated", isAuthenticated);

        User loggedInUser;
        try {
            loggedInUser = userService.getLoggedInUser();
        } catch (UsernameNotFoundException e) {
            loggedInUser = null;
        }

        model.addAttribute("user",loggedInUser);
//
//        Object greeting = session.getAttribute("greeting");
//        if(greeting!=null)
//            model.addAttribute("greeting",session.getAttribute("greeting"));
        return "home";
    }
}
