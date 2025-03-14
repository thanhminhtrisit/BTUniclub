package com.cyber08.uniclub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//      muốn thêm nhiều đường dẫn thì thêm dấu , authorizeRequests.requestMatchers("/auth/sign-in","....").permitAll();
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->{
                    authorizeRequests.requestMatchers("/auth/sign-in", "/auth/sign-up").permitAll();
                    authorizeRequests.anyRequest().authenticated();
                }).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
