package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;

    @Override
    public List<FestivalDTO> getAllFestivalsAsDTO() {
        List<Festival> festivals = festivalRepository.findAll();
        List<FestivalDTO> festivalDTOs = new ArrayList<>();

        for (Festival festival : festivals) {
            FestivalDTO festivalDTO = new FestivalDTO(festival.getFestivalTitle(), festival.getThumbnail(),
                    festival.getPoster());
            festivalDTOs.add(festivalDTO);
        }

        return festivalDTOs;
    }

    @Override
    public List<FestivalDTO> getFiveDTOs() {

        List<Festival> fetival5List = festivalRepository.getFiveEntity();

        return fetival5List.stream().map(festivals -> entityToDTO(festivals)).collect(Collectors.toList());
    }

    // 전체 가져와서 순서대로
    @Override
    public List<FestivalDTO> findAllByOrderByFestivalNumAsc() {

        List<Festival> festivalAll = festivalRepository.findAllByOrderByFestivalNumAsc();

        return festivalAll.stream().map(festivals -> entityToDTO(festivals)).collect(Collectors.toList());

    }

    // 축제 페이징
    @Override
    public Page<FestivalDTO> findAllByOrderByFestivalNum(Pageable pageable) {
        Page<Festival> festivalPage = festivalRepository.findAllByOrderByFestivalNum(pageable);
        return festivalPage.map(festival -> entityToDTO(festival));
    }

    // 축제페이지에서 클릭시 상세페이지로..
    // @Override
    // public List<FestivalDTO> getList(Long festivalNum) {

    // Optional<Festival> festival =
    // festivalRepository.findByFestivalNum(festivalNum);
    // if (festival != null) {
    // return entityToDTO(festival);
    // } else {
    // // 해당 festivalNum에 해당하는 축제가 없을 경우 처리
    // return null;
    // }
    // }

    // 축제페이지에서 클릭시 상세페이지로
    @Override
    public FestivalDTO getfestivalFNum(Long festivalNum) {
        Festival festival = festivalRepository.findById(festivalNum)
                .orElseThrow(() -> new NoSuchElementException(festivalNum + "게시물을 찾을 수 없습니다."));
        return entityToDTO(festival);
    }

}
