package com.whiteboard.whiteboard.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class UserDetailSeviceImpl implements UserDetailsService{
    
    private final MemberRepository memberRepository;
    
    

    @Override
    public UserDetails loadUserByUsername(String email){
        Member member = memberRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException("찾을수없는 아이디: "+email));
        
        return UserDetailSeviceImpl(member);
    }



    private UserDetails UserDetailSeviceImpl(Member member) {
        return null;
    }

    

    
}
