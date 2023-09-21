package com.whiteboard.whiteboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.forAPI.BusanDTO;

@Service
public interface FestivalService {

       public List<BusanDTO> getRandomFestivals();
   
}
