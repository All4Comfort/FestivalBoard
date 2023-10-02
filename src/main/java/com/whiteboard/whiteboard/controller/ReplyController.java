package com.whiteboard.whiteboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.whiteboard.whiteboard.dto.ReplyDTO;
import com.whiteboard.whiteboard.service.FestivalReplyService;
import com.whiteboard.whiteboard.service.ReviewReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final FestivalReplyService festivalReplyService;
    private final ReviewReplyService reviewReplyService;
    
    // ResponseEntity는 Spring Framework에서 사용되는 클래스로, HTTP 응답을 생성하고 반환하는 데 사용
    // 클라이언트에게 HTTP 응답을 보내는 데 필요한 모든 정보를 포함
    // 응답의 상태 코드와 내용을 설정할 수 있어 클라이언트에게 적절한 응답을 보내는 데 유용
    // HTTP 상태 코드, 응답 본문, 헤더 등을 설정할 수 있는 방법을 제공

    //댓글 등록하는 Post 메서드 정의
    @PostMapping("/review/save")
    public ResponseEntity saveFestivalReply(@ModelAttribute ReplyDTO replyDTO, HttpSession session) {
        System.out.println("replyDTO = " + replyDTO);
        Long saveResult = reviewReplyService.save(replyDTO);
        if (saveResult != null) {
             List<ReplyDTO> replyDTOList = festivalReplyService.findAll(replyDTO.getReviewNum(), session);
             return new ResponseEntity<>(replyDTOList, HttpStatus.OK);
         } else {
             return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
         }
    }

    
  
 
}
