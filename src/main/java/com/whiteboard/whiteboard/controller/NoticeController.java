package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.dto.NoticeDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.entity.Notice;
import com.whiteboard.whiteboard.repository.NoticeRepository;
import com.whiteboard.whiteboard.service.NoticeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class NoticeController {
  private final NoticeService noticeService;
  private final NoticeRepository noticeRepository;

  @GetMapping("/notice1")
  public String notice(PageRequestDTO pageRequestDTO, Model model){
    //model.addAttribute("result", noticeService.getList(pageRequestDTO));
    //model.addAttribute("result", noticeRepository.getNoticeBynoticeNum(1L));
    model.addAttribute("result", noticeRepository.getNoticeList());
    
    return "/notice1";
  }


@GetMapping("/notice1Detail")
public void noticeDetail(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Long noticeNum, Model model){
    NoticeDTO noticeDTO = noticeService.get(noticeNum);
    System.out.println(noticeDTO);
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
    return "redirect:/notice1";
  }

  @GetMapping("/member/delete")
    public String delete(HttpSession session, @ModelAttribute MemberDTO memberDTO) {

        // 세션에서 현재 로그인한 사용자 정보를 가져옵니다.
        Notice loggedInUser = (Notice) session.getAttribute("loggedInUser");

        noticeRepository.delete(loggedInUser);

        // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트 처리
        return "redirect:/main";
    }


}
