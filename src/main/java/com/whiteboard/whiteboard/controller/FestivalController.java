package com.whiteboard.whiteboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

@Controller
public class FestivalController {

     @Autowired
    private FestivalRepository festivalRepository;

    @GetMapping("/test")
    public String listFestivals(Model model) {
        List<Festival> festivals = festivalRepository.findAll();
        model.addAttribute("test", festivals);
        return "test"; // HTML 템플릿 파일 이름을 지정합니다.
    }
}
