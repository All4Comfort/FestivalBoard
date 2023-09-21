package com.whiteboard.whiteboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.FestivalDTO;

@Service
public interface FestivalService {

      // public List<FestivalDTO> getRandomFestivals();

       public List<FestivalDTO> getAllFestivalsAsDTO();
   
}
