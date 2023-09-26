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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteboard.whiteboard.dto.NoticeDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.repository.NoticeRepository;
import com.whiteboard.whiteboard.repository.QuestionRepository;
import com.whiteboard.whiteboard.service.NoticeService;
import com.whiteboard.whiteboard.service.NoticeServiceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")

public class NoticeController {
  private final NoticeService noticeService;
  private final NoticeServiceImpl noticeServiceImpl;
  private final NoticeRepository noticeRepository;
  private final QuestionRepository questionRepository;

  // @GetMapping("/notice1")
  // public void notice(PageRequestDTO pageRequestDTO, Model model){
  //   //model.addAttribute("result", noticeService.getList(pageRequestDTO));
  //   //model.addAttribute("result", noticeRepository.getNoticeBynoticeNum(1L));
    
  //   model.addAttribute("result", noticeService.findAll());
  //   //model.addAttribute("result", noticeRepository.getNoticeList());
  // }

  // 페이징을 위해
    @GetMapping("/notice1")
    public String notice(PageRequestDTO pageRequestDTO, Model model) {
        // 페이지 요청 정보(pageRequestDTO)를 이용하여 페이지 관련 정보를 가져오고
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("noticeNum").descending());

        // 서비스 계층을 통해 페스티벌 데이터를 페이지네이션하여 가져옵니다.
        Page<NoticeDTO> notices = noticeService.findAllByOrderByNoticeNum(pageable);

        // 총 페이지 수 계산
        int totalPages = notices.getTotalPages();

        // 현재 페이지 번호 가져오기
        int currentPage = pageRequestDTO.getPage();

        // 페이지 번호 목록 생성 (고정된 3개 페이지만 표시)
        // 현재 페이지를 중심으로 앞뒤 1페이지만 보여주도록 제한
        List<Integer> pageNumbers = IntStream
                .rangeClosed(Math.max(1, currentPage - 1), Math.min(currentPage + 1, totalPages))
                .boxed()
                .collect(Collectors.toList());

        // 이전 페이지와 다음 페이지 버튼을 제어하기 위한 조건 설정
        boolean hasPrevPage = currentPage > 1; // 현재 페이지가 1보다 크면 이전 페이지가 있다는 의미
        boolean hasNextPage = currentPage < totalPages; // 현재 페이지가 총 페이지 수보다 작으면 다음 페이지가 있다는 의미

        // 이전 페이지 번호와 다음 페이지 번호 계산
        int prevPageNumber = currentPage - 1;
        int nextPageNumber = currentPage + 1;

        // 모델에 데이터를 추가하여 뷰로 전달
        model.addAttribute("festivals", notices); // 페스티벌 데이터
        model.addAttribute("pageNumbers", pageNumbers); // 페이지 번호 목록
        model.addAttribute("hasPrevPage", hasPrevPage); // 이전 페이지 버튼 제어를 위한 조건
        model.addAttribute("hasNextPage", hasNextPage); // 다음 페이지 버튼 제어를 위한 조건
        model.addAttribute("prevPageNumber", prevPageNumber); // 이전 페이지 번호
        model.addAttribute("nextPageNumber", nextPageNumber); // 다음 페이지 번호

        // 해당 뷰 페이지로 이동
        return "/notice/notice1";
    }

@GetMapping("/notice1Detail")
public void noticeDetail(@RequestParam("noticeNum") Long noticeNum, Model model, HttpSession session) {
    NoticeDTO noticeDTO = noticeService.get(noticeNum);
    model.addAttribute("result", noticeDTO);

}

//신규글등록폼 요청처리하기
  @GetMapping("/noticeWrite")
  public void noticeWrite(){
    
  }

  //신규글 등록처리하기..
  @PostMapping("/noticeWrite")
  public String register(@ModelAttribute NoticeDTO dto, RedirectAttributes attributes){
   //Notice notice = 
   // notice.updateTitle(dto.getTitle());
   // notice.updateNContent(dto.getContent());
  //  noticeRepository.save(notice);
    Long newNoticeNum = noticeService.register(dto);
    attributes.addFlashAttribute("newNoticeNum", newNoticeNum);
    return "redirect:/notice/notice1";
  }

  @PostMapping("/remove")
  public String remove(long noticeNum, RedirectAttributes redirect){
    System.out.println("GGGGGGGGG");
    noticeService.remove(noticeNum);
    redirect.addAttribute("newNoticeNum", noticeNum);
    return "redirect:/notice/notice1";
  }

  @PostMapping("/noticemodify")
  public String modify(NoticeDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirect){
    System.out.println("수정창띄우기!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    //System.err.println("dto!!!!!!!!!!!!!!!!!!!!! : " + dto);
    noticeService.modify(dto);
    
    //redirect.addAttribute("page", requestDTO);
    redirect.addAttribute("noticeNum", dto.getNoticeNum());
    //System.out.println(redirect.addAttribute("page", requestDTO));
    return "redirect:/notice/notice1Detail";
  }

  //신규글등록폼 요청처리하기
  @GetMapping("/noticemodify")
  public void noticemodify(@ModelAttribute NoticeDTO dto, Model model){
    
    model.addAttribute("dto", dto);
  }

  @GetMapping("/question")
  public void question(PageRequestDTO pageRequestDTO, Model model){
    //model.addAttribute("result", noticeService.getList(pageRequestDTO));
    //model.addAttribute("result", noticeRepository.getNoticeBynoticeNum(1L));
    
    //model.addAttribute("result", noticeService.findAllQuestion());
    model.addAttribute("result", questionRepository.getQuestionList());
  }

//   @GetMapping("/questionDetail")
// public void questionDetail(@RequestParam("questionNum") Long questionNum, Model model, HttpSession session) {
//     QuestionDTO questionDTO = noticeService.getquestion(questionNum);
//     model.addAttribute("result", questionDTO);

// }

}
