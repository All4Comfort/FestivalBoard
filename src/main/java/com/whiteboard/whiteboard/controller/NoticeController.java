package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.repository.NoticeRepository;
import com.whiteboard.whiteboard.service.NoticeService;

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
  public void noticeDetail(){

  }

  @GetMapping("/noticeWrite")
  public void noticeWrite(){
    
  }
}
