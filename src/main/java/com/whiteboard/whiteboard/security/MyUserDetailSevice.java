package com.whiteboard.whiteboard.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.entity.AuthoritySet;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class MyUserDetailSevice implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String UserEmail) throws UsernameNotFoundException {
        Optional<Member> member = this.memberRepository.findByEmail(UserEmail);
        if (member.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member members = member.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(UserEmail)) {
            authorities.add(new SimpleGrantedAuthority(AuthoritySet.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(AuthoritySet.USER.getValue()));
        }
        return new User(members.getEmail(), members.getPassword(), authorities);

    }

}
