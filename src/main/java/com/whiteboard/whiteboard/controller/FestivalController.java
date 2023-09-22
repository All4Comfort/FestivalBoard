package com.whiteboard.whiteboard.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.service.FestivalService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
// @RequestMapping("/member")
public class FestivalController {

    private final FestivalService festivalService;
    //밑 코드는 모듈에 메시지 넣기위해 필요해서 넣었음
    // private final Logger logger = LoggerFactory.getLogger(FestivalController.class);

    // 한울쓰//한울쓰~~ 이건 모듈에 반환하는거라 에러 났었데 그래서 지웁니다
    // @GetMapping("/member/main")
    // public void getFestivals(Model model) {

    //     // 모델에 데이터 추가
    //     List<FestivalDTO> festivals = festivalService.getFiveDTOs();
    //     logger.info("축제 목록: {}", festivals);

    //     // 모델에 데이터 추가
    //     model.addAttribute("festivals", festivalService.getFiveDTOs());

    // }

    @GetMapping("/member/main")
    @ResponseBody // 이 어노테이션을 추가하여 메서드가 JSON 데이터를 반환함
    public List<FestivalDTO> getFestivals() {
        List<FestivalDTO> festivals = festivalService.getFiveDTOs();
       // logger.info("축제 목록: {}", festivals); //콘솔에 찍히나 확인했습니다.
        return festivals; // JSON 형식의 데이터를 반환합니다.
    }

    // 3번째 방법
    /*
     * @GetMapping("/test")
     * public String getFestivals(Model model) {
     * // 랜덤한 축제 목록 생성 (실제로는 서비스 레이어를 통해 데이터를 가져옵니다)
     * List<FestivalDTO> festivals = createRandomFestivals();
     * 
     * // 모델에 데이터 추가
     * model.addAttribute("festivals", festivals);
     * 
     * return "test"; // 타임리프 템플릿 이름
     * }
     */

    // 실제 데이터를 가져오는 로직은 이곳에서 수행해야 합니다.
    private List<FestivalDTO> createRandomFestivals() {
        // 랜덤한 축제 목록 생성 및 반환
        // 실제 데이터를 조회하거나 서비스를 통해 데이터를 가져오는 로직을 구현해야 합니다.
        return Arrays.asList(
                new FestivalDTO("축제 1", "링크 1"),
                new FestivalDTO("축제 2", "링크 2"),
                new FestivalDTO("축제 3", "링크 3"),
                new FestivalDTO("축제 4", "링크 4"),
                new FestivalDTO("축제 5", "링크 5"));
    }
}
