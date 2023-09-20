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
        // 랜덤한 5개의 진행중 축제 가져오기
        List<Festival> randomOngoingFestivals = festivalRepository.findRandomFestival();
        return randomOngoingFestivals;
    }
}
