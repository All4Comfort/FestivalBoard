package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

@Service
// @RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;

    @Autowired
    public FestivalServiceImpl(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }

    // 1번째 시도
    // @Override
    // public List<FestivalDTO> getRandomFestivals() {
    //// return festivalRepository.findRandomFive();
    // }

    // 2번째 시도
    @Override
    public List<FestivalDTO> getAllFestivalsAsDTO() {
        List<Festival> festivals = festivalRepository.findAll();
        List<FestivalDTO> festivalDTOs = new ArrayList<>();

        for (Festival festival : festivals) {
            FestivalDTO festivalDTO = new FestivalDTO(festival.getFestivalTitle(), festival.getThumbnail());
            festivalDTOs.add(festivalDTO);
        }

        return festivalDTOs;
    }

}
