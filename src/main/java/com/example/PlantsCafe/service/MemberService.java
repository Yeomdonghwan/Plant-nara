package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.MemberEntity;
import com.example.PlantsCafe.dto.MemberDto;
import com.example.PlantsCafe.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {

    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }


    public void createMember(MemberDto memberDto) {
        log.info("DTO => {}",memberDto.toString());

        MemberEntity memberEntity = MemberEntity.createMemberEntity(memberDto);

        log.info("Entity => {}",memberEntity.toString());
        //중복유저는 안됨
        validDataDuplecateMember(memberDto);

        MemberEntity saved = memberRepository.save(memberEntity);
        log.info("saved => {}",saved.toString());
    }

    private void validDataDuplecateMember(MemberDto memberDto) {
        memberRepository.findByUserId(memberDto.getUserId())
                        .ifPresent(m -> {
                            throw new IllegalStateException("valid userId");
                        });
    }
}
