package com.example.PlantsCafe.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user1")
                .password("user1password")
                .roles("USER");
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                    .requestMatchers("/login").permitAll()// /login으로의 접근 설정. (permitAll)
                    .requestMatchers("/forum").permitAll()
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/signUp").permitAll()
                    .requestMatchers("/plant").permitAll()
                    .anyRequest().authenticated()//지정url 이외 모든 url
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/doLogin")
                    .usernameParameter("id")
                    .passwordParameter("pw")
                    .successHandler(new MyLoginSuccessHandler())
                .and()
                .logout()
                .logoutUrl("/doLogout")
                .logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}