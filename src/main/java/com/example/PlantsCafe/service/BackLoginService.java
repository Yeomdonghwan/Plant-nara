package com.example.PlantsCafe.service;

import com.example.PlantsCafe.Entity.User;
import com.example.PlantsCafe.dto.UserDto;
import com.example.PlantsCafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service @Transactional @RequiredArgsConstructor
public class BackLoginService implements UserDetailsService {

//    @Autowired
    private final UserRepository userRepository;

//    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 유저입니다"));

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }

    public void addUser(UserDto userDto) {
        validDataDuplecateMember(userDto);

        User userEntity = User.builder()
                .name(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role("user")
                .nickname(userDto.getNickname())
                .build();

        userRepository.save(userEntity);
    }
        private void validDataDuplecateMember(UserDto userDto) {
        userRepository.findByName(userDto.getUserId())
                        .ifPresent(m -> {
                            throw new IllegalStateException("valid userId");
                        });
    }
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        return userRepository.findByName(loggedInUsername)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));
    }
}
