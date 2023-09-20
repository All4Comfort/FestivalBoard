package com.whiteboard.whiteboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

@Controller

public class FestivalController {
    
    @Autowired
    private FestivalRepository festivalRepository;


    @GetMapping("/test")
    public List<Festival> getOngoingFestivals(){
        //축제 API데이터 를 DB에서 가져옴
        List<Festival> ongoingFestivals = festivalRepository.findOngoingFestivals();
        return ongoingFestivals;
    }
}
