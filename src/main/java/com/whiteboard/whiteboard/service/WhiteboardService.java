package com.whiteboard.whiteboard.service;

import com.whiteboard.whiteboard.dto.MemberDTO;

public interface WhiteboardService {
    
    //신규 회원 가입 메서드
    String memberRegister(MemberDTO memberDTO);

    //회원이  삭제하는 메서드
    void deleteMember(String memberId);

    //회원 수정 메서드
    void updateMember(String memberId);




}
