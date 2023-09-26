package com.whiteboard.whiteboard.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.repository.FestivalRepository;
import com.whiteboard.whiteboard.service.FestivalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// @RequestMapping("/member")//멤버 기본경로

public class FestivalController {

    private final FestivalService festivalService;

    private final FestivalRepository festivalRepository;

    // 메인화면에서 롤페이지 5개 랜덤 이미지 포스터
    @GetMapping("/member/main")
    @ResponseBody // 이 어노테이션을 추가하여 메서드가 JSON 데이터를 반환함
    public List<FestivalDTO> getFestivals() {
        List<FestivalDTO> festivals = festivalService.getFiveDTOs();
        // logger.info("축제 목록: {}", festivals); //콘솔에 찍히나 확인했습니다.
        return festivals; // JSON 형식의 데이터를 반환합니다.
    }

    // 전체 죽제 목록을 가져오기
    // @GetMapping("/festival/festivalList")
    // public String showFestivalList(PageRequestDTO pageRequestDTO, Model model) {
    //     List<FestivalDTO> festivals = festivalService.findAllByOrderByFestivalNumAsc();
    //     // System.out.println("축제 목록: " + festivals);
    //     model.addAttribute("festivals", festivals);
    //     return "/festival/festivalList";
    // }

    //페이징을 위해
    @GetMapping("/festival/festivalList")
public String showFestivalList(PageRequestDTO pageRequestDTO, Model model) {
    Pageable pageable = pageRequestDTO.getPageable(Sort.by("festivalNum").ascending());
    Page<FestivalDTO> festivals = festivalService.findAllByOrderByFestivalNum(pageable);
    model.addAttribute("festivals", festivals);

    // 총 페이지 수 계산
    int totalPages = festivals.getTotalPages();

    // 현재 페이지 번호 가져오기
    int currentPage = pageRequestDTO.getPage();

    // 페이지 번호 목록 생성
    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
            .boxed()
            .collect(Collectors.toList());

    // 이전 페이지 및 다음 페이지 버튼 처리
    boolean hasPrevPage = currentPage > 1;
    boolean hasNextPage = currentPage < totalPages;

    model.addAttribute("pageNumbers", pageNumbers);
    model.addAttribute("hasPrevPage", hasPrevPage);
    model.addAttribute("hasNextPage", hasNextPage);

    return "/festival/festivalList";
}
    
    // 축제 상세페이지 넘기기
    @GetMapping("/festival/festivalDetail")
    public String getFestivalDetail(@RequestParam("festivalNum") Long festivalNum, Model model) {
        FestivalDTO festivalDTO = festivalService.getfestivalFNum(festivalNum);
        model.addAttribute("festivalDTO", festivalDTO);
        // System.out.println("festivalNum 숫자: " + festivalDTO);
        return "festival/festivalDetail"; // 렌더링할 뷰의 이름을 반환
    }
}