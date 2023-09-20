package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class NoticeController {
  private final NoticeService noticeService;


  @GetMapping("/notice1")
  public String notice(PageRequestDTO pageRequestDTO, Model model){
    model.addAttribute("result", noticeService.getList(pageRequestDTO));
    return "/notice1";
  }

}
