package com.whiteboard.whiteboard.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        }
        return "user";
    }

}
