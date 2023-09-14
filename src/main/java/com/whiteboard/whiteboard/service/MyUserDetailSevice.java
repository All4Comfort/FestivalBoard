package com.whiteboard.whiteboard.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class MyUserDetailSevice implements UserDetailsService{
    
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Member> findOne = memberRepository.findByEmail(userName);
        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("가입되지 않은 ID 입니다: "+userName));

        return User.builder()
                .username(member.getUsername())
                .password(member.getPw())
                
                .build();
    }
    
    

   



   

    

    
}
