package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Membercontroller {
    
    @GetMapping("/login")
    public String loging(){

        return loging();
    }
}
