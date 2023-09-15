package com.whiteboard.whiteboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsService memberService;

    ////whiteboard의 모든 페이지의 기본값으로 스프링 시큐리티 기능 비활성화 메서드
    //인증을 위해 필요(?)
    @Bean
    public WebSecurityCustomizer inactvatSecurity() { 
        //WebSecurityCustomizer는 Spring Security의 설정을 사용자 정의하기 위한 함수형 인터페이스

        return (web) -> web.ignoring() //web 파라미터는 WebSecurity 객체
                //.requestMatchers(toH2Console())
                .requestMatchers("/whiteboard/**"); ///whiteboard/" 경로로 시작하는 모든 요청을 무시하도록 설정
                
    }
    
    //특정 HTTP 요청에 대한 웹 기반 보안 설정 메서드
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(request -> request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
        .requestMatchers("/main", "//**", "/view/join", "/auth/join").permitAll()
        .requestMatchers("/dashBoard").hasRole("ADMIN")
        .requestMatchers("/user").hasRole("USER")
        .anyRequest().authenticated())
        .formLogin(login -> login
        .loginPage("/login")
        
        .loginProcessingUrl("/login-process")
        .usernameParameter("email")
        .passwordParameter("pw")
        .defaultSuccessUrl("/user", true)
        .permitAll())
        .logout()
        .logoutUrl("logout")
        .permitAll()
        .and()
        .csrf().disable() //csrf 비활성화
        .cors().disable() //cors 비활성화
        .build();
        
        return http.build();
    }
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    //비밀번호 인코더로 사용할 빈 등록
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
