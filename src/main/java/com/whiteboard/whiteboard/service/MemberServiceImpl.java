package com.whiteboard.whiteboard.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Override
    public String memberRegister(MemberDTO memberDTO) {

        Member member = Member.builder()
                .id(memberDTO.getId())
                .pw(memberDTO.getPassword())
                .phoneNum(memberDTO.getPhoneNum())
                .nickname(memberDTO.getNickname())
                .gender(memberDTO.getGender())
                .birthDay(memberDTO.getBirthDay())
                .name(memberDTO.getName())
                .isSns(memberDTO.isSns())
                .build();
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 삭제
    @Override
    public void memberDelete(String memberId) {
        memberRepository.deleteById(memberId);
    }

    //회원정보 수정
    @Override
    public void memberUpdate(String memberId) {
        memberRepository.findById(memberId);
    }

}