package com.whiteboard.whiteboard.controller;

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

  @GetMapping("/notice1")
  public void notice(PageRequestDTO pageRequestDTO, Model model){
    //model.addAttribute("result", noticeService.getList(pageRequestDTO));
    //model.addAttribute("result", noticeRepository.getNoticeBynoticeNum(1L));
    
    model.addAttribute("result", noticeService.findAll());
    //model.addAttribute("result", noticeRepository.getNoticeList());
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

  @PostMapping("/modify")
  public String modify(NoticeDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirect){
    noticeService.modify(dto);
    
    redirect.addAttribute("page", requestDTO);
    redirect.addAttribute("noticeNum", dto.getNoticeNum());
    return "redirect:/notice/notice1";
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
