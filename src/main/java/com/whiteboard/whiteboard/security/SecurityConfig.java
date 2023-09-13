package com.whiteboard.whiteboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity https) throws Exception{

        //각 경로마다 접근 가능한 권한 설정인가(authorization) 메서드
        https.authorizeHttpRequests((auth) -> {

            auth.requestMatchers("/whiteboard/Main").permitAll(); // 모두에게 허용
            auth.requestMatchers("/whiteboard/myPage").hasRole("MEMBER");// 회원에게만 허용
            auth.requestMatchers("/whiteboard/dashBoard").hasRole("ADMIN");//관리자에게만 허용
        
        });

        https.formLogin(); // 로그인 페이지로 반환
        https.csrf().disable();
        https.logout();

        return https.build();
    }
}
