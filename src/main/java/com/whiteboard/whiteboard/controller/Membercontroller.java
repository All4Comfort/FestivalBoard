package com.whiteboard.whiteboard.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.whiteboard.whiteboard.dto.MemberDTO;
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
}
