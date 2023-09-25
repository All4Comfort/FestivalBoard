package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;

import com.whiteboard.whiteboard.service.FestivalReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final FestivalReplyService festivalReplyService;

    //댓글 등록 페이지
    // @PostMapping("/save")
    // public ResponseEntity saveReply(@ModelAttribute ReplyDTO replyDTO) {
    //     System.out.println("replyDTO = " + replyDTO);
    //     Long saveResult = festivalReplyService.register(replyDTO);
        
    // }

}
