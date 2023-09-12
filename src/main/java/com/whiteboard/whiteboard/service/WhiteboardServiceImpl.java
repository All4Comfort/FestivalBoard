package com.whiteboard.whiteboard.service;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WhiteboardServiceImpl implements WhiteboardService {

    private final MemberRepository memberRepository;

    @Override
    public String memberRegister(MemberDTO memberDTO) {

        Member member = Member.builder()
                .id(memberDTO.getId())
                .password(memberDTO.getPassword())
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

}
