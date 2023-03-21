package com.example.PlantsCafe.controller;

import com.example.PlantsCafe.dto.MemberDto;
import com.example.PlantsCafe.repository.MemberRepository;
import com.example.PlantsCafe.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller @Slf4j
public class MemberController {

    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService=memberService;
    }

    @GetMapping("/members/join")
    public String join(){
        return "/members/join";
    }

    @PostMapping("/members/create")
    public String createMember(MemberDto memberDto){
        log.info("First DTO => {}",memberDto);
        memberService.createMember(memberDto);
        return "redirect:/";
    }
}
