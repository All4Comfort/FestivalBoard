package com.whiteboard.whiteboard.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.whiteboard.whiteboard.dto.FestivalDTO;

@Controller
//@RequestMapping("/test")
public class FestivalController {

    //  private final FestivalService festivalService;

    // @Autowired
    // public FestivalController(FestivalService festivalService) {
    //     this.festivalService = festivalService;
    // }

    // @GetMapping("/test")
    // public List<FestivalDTO> getRandomFiveFestivals() {
    //     return festivalService.getRandomFestivals();
    // }

    //3번째 방법
    @GetMapping("/test")
    public String getFestivals(Model model) {
        // 랜덤한 축제 목록 생성 (실제로는 서비스 레이어를 통해 데이터를 가져옵니다)
        List<FestivalDTO> festivals = createRandomFestivals();

        // 모델에 데이터 추가
        model.addAttribute("festivals", festivals);

        return "test"; // 타임리프 템플릿 이름
    }
    // 실제 데이터를 가져오는 로직은 이곳에서 수행해야 합니다.
    private List<FestivalDTO> createRandomFestivals() {
        // 랜덤한 축제 목록 생성 및 반환
        // 실제 데이터를 조회하거나 서비스를 통해 데이터를 가져오는 로직을 구현해야 합니다.
        return Arrays.asList(
            new FestivalDTO("축제 1", "링크 1"),
            new FestivalDTO("축제 2", "링크 2"),
            new FestivalDTO("축제 3", "링크 3"),
            new FestivalDTO("축제 4", "링크 4"),
            new FestivalDTO("축제 5", "링크 5")
        );
    }
}
