package com.whiteboard.whiteboard.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.entity.Member;

// 회원 관리와 관련된 비즈니스 로직을 수행하는 서비스
// 회원 가입, 로그인, 회원 정보 조회 및 수정 등과 같은 회원 관련 기능을 제공
// 주로 데이터베이스 CRUD(Create, Read, Update, Delete) 작업을 처리

@Service
public interface MemberService {

    // 신규 회원 가입 메서드
    String memberRegister(Member member);

    // 회원이 삭제하는 메서드
    void memberDelete(String email);

    // 회원 수정 메서드
    void memberUpdate(String email);

    static boolean isValidMember(String email, String pw) {
        return false;
    }

    // 폰번호 중복 확인
    public boolean isPhoneNumberRegistered(String phoneNum);

    // 닉네임 중복 확인
    public boolean isNicknameRegistered(String nickname);

    // 이메일 중복 확인 메서드
    public boolean isEmailRegistered(String email);

    //로그인 메서드
    public Optional<Member> login(String email, String pw);

}
