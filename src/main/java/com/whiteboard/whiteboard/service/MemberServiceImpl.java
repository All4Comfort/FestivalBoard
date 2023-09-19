package com.whiteboard.whiteboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    public String memberRegister(Member member) {

        // Member member = Member.builder()
        // .email(memberDTO.getEmail())
        // .pw(memberDTO.getPw())
        // .phoneNum(memberDTO.getPhoneNum())
        // .nickname(memberDTO.getNickname())
        // .gender(memberDTO.getGender())
        // .birthDay(memberDTO.getBirthDay())
        // .name(memberDTO.getName())
        // .isSns(memberDTO.isSns())
        // .build();
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

    // 이메일 중복 검사 메서드
    @Override
    public boolean isEmailRegistered(String email) {
        Optional<Member> existingMember = memberRepository.findByEmail(email);
        // 이미 존재하는 이메일인 경우
        if (existingMember.isPresent()) {
            return true; // 중복된 이메일이 있음을 나타내는 true 반환
        } else {
            return false; // 중복된 이메일이 없음을 나타내는 false 반환
        }
    }

    // 전화번호 중복 검사 메서드
    @Override
    public boolean isPhoneNumberRegistered(String phoneNum) {
        Optional<Member> existingMember = memberRepository.findByphoneNum(phoneNum);
        // 이미 존재하는 전화번호인 경우
        if (existingMember.isPresent()) {
            return true; // 중복된 전화번호가 있음을 나타내는 true 반환
        } else {
            return false; // 중복된 전화번호가 없음을 나타내는 false 반환
        }
    }

    // 닉네임 중복 검사 메서드
    @Override
    public boolean isNicknameRegistered(String nickname) {
        Optional<Member> existingMember = memberRepository.findBynickname(nickname);
        // 이미 존재하는 닉네임인 경우
        if (existingMember.isPresent()) {
            return true; // 중복된 닉네임이 있음을 나타내는 true 반환
        } else {
            return false; // 중복된 닉네임이 없음을 나타내는 false 반환
        }
    }

    @Override
    public Optional<Member> login(String email, String pw) {
        // 이메일로 사용자 검색
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        

        // 사용자가 존재하고 비밀번호가 일치하는 경우 Optional<Member> 반환
        return optionalMember.filter(member -> member.getPw().equals(pw));
    }

}