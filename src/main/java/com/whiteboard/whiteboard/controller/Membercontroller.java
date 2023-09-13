package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Main")
@Controller
public class Membercontroller {
    

    @GetMapping("/Main")
    public String main(){
        return main();
    }

    @GetMapping("/login")
    public String loging(){

        return loging();
    }
}
