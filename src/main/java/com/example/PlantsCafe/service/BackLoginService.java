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



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username).orElseThrow(()-> new IllegalArgumentException("not exist1"));

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRole())));
    }



}
