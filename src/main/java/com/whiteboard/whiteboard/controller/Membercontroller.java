package com.whiteboard.whiteboard.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;
import com.whiteboard.whiteboard.service.MemberService;


@Controller
public class Membercontroller {
    

    @GetMapping("/Main")
    public String main(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userName = auth.getName();

        return "Main";
    }

    @GetMapping("/login")
    public String loging(){

        return "login";
    }
    @PostMapping("/login-process")
    public String login (MemberDTO dto){
        boolean isValidMember = MemberService.isValidMember(dto.getEmail(),dto.getPw());
        if(isValidMember)
         return "dashBoard";
        return "login";
    }

    // @GetMapping("/user")
    // public String dashBoardPage(@AuthenticationPrincipal UserDetails user, Model model){
    //     model.addAttribute("loginId",user.getUsername() );
    //     model.addAttribute("loginRoles",user.getAuthorities() );
    //     return "user";
    // }


    @Autowired
    public Membercontroller(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

     MemberRepository memberRepository;

    @GetMapping("/user")
    public String dashBoardPage(@AuthenticationPrincipal UserDetails user, Model model) {
       
        // 사용자 이름을 기반으로 데이터베이스에서 정보 가져오기
        Optional<Member> memberOptional = memberRepository.findByEmail(user.getUsername());
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            model.addAttribute("loginName", member.getName());
            model.addAttribute("loginId", member.getEmail());
            model.addAttribute("loginRoles", user.getAuthorities());
            model.addAttribute("phoneNumber", member.getPhoneNum());
            model.addAttribute("nickname", member.getNickname());
            model.addAttribute("birthDay", member.getBirthDay());
        }
        return "user";
    }

    //회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@RequestBody Member member) {
        // 회원 정보를 저장하고 결과를 반환
        try {
            memberRepository.save(member);
            return ResponseEntity.ok("회원 가입이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 중 오류가 발생했습니다.");
        }
    }

}
