package com.whiteboard.whiteboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    //private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    public String memberRegister(MemberDTO memberDTO) {

        Member member = Member.builder()
                .email(memberDTO.getEmail())
                .pw(memberDTO.getPw())
                .phoneNum(memberDTO.getPhoneNum())
                .nickname(memberDTO.getNickname())
                .gender(memberDTO.getGender())
                .birthDay(memberDTO.getBirthDay())
                .name(memberDTO.getName())
                .isSns(memberDTO.isSns())
                .build();
            // 회원 정보를 저장        
        memberRepository.save(member);

        return member.getEmail();
    }

    // 회원 삭제
    @Override
    public void memberDelete(String email) {
        memberRepository.deleteById(email);
    }

    // 회원정보 수정
    @Override
    public void memberUpdate(String email) {
        memberRepository.findByEmail(email);
    }

    public Optional<Member> findOne(String email){
        return memberRepository.findByEmail(email);
    }

    

}