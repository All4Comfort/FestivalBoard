package com.whiteboard.whiteboard.controller;

import org.springframework.stereotype.Controller;

import com.whiteboard.whiteboard.service.FestivalReplyService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final FestivalReplyService festivalReplyService;
/*
    //댓글 등록 페이지
    @PostMapping("/festival/save")
    public ResponseEntity saveFestivalReply(@ModelAttribute ReplyDTO replyDTO) {
        System.out.println("replyDTO = " + replyDTO);
        Long saveResult = festivalReplyService.register(replyDTO);
    }

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        Long saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
    }
 */
}
