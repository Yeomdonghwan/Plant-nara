package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.UserDto;
import com.example.PlantsCafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void addUser(UserDto userDto) {
        if(userRepository.existsByName(userDto.getUserId())){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        User userEntity = User.builder()
                .name(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role("user")
                .nickname(userDto.getNickname())
                .build();

        userRepository.save(userEntity);
    }
//    private void validDataDuplecateMember(UserDto userDto) {
//        userRepository.findByName(userDto.getUserId())
//                        .ifPresent(m -> {
//                            throw new IllegalStateException("valid userId");
//                        });
//    }

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        return userRepository.findByName(loggedInUsername)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
    }
}
