package com.whiteboard.whiteboard.service;

import java.util.List;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.entity.Festival;

public interface FestivalService {

      //한울쓰
      List<FestivalDTO> getFiveDTOs();

      // public List<FestivalDTO> getRandomFestivals();

       public List<FestivalDTO> getAllFestivalsAsDTO();

       //Entity를 DTO로 변환하는 메서드 선언
	default FestivalDTO entityToDTO(Festival festival) {
		
		FestivalDTO dto = FestivalDTO.builder()
                              .festivalNum(festival.getFestivalNum())
					.festivalTitle(festival.getFestivalTitle())
					.region(festival.getRegion())
                              .venue(festival.getVenue())
                              .period(festival.getPeriod())
                              .description(festival.getDescription())
                              .link(festival.getLink())
                              .poster(festival.getPoster())
                              .thumbnail(festival.getThumbnail())
                              .registerDate(festival.getRegisterDate())
                              .modifyDate(festival.getModifyDate())
                              
					.build(); 
		return dto;
	}
   
}
