package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;

/* 안쓰는.... 위에 선언됐으니...
    @Autowired
    public FestivalServiceImpl(FestivalRepository festivalRepository) {
        this.festivalRepository = festivalRepository;
    }
*/
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

    @Override
    public List<FestivalDTO> getFiveDTOs() {

        List<Festival> fetival5List = festivalRepository.getFiveEntity();

        return fetival5List.stream().map(festivals -> entityToDTO(festivals)).collect(Collectors.toList());
    }

}
