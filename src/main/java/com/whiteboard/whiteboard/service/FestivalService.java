package com.whiteboard.whiteboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.FestivalBusanDTO;

@Service
public interface FestivalService {

       public List<FestivalBusanDTO> getRandomFestivals();
   
}
