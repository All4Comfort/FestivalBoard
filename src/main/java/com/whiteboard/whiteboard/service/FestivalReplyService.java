package com.whiteboard.whiteboard.service;

import java.util.List;

import com.whiteboard.whiteboard.dto.ReplyDTO;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.entity.FestivalReply;

public interface FestivalReplyService {


  List<ReplyDTO> getAllReply(Long festivalNum);
  
  List<ReplyDTO> register(ReplyDTO replyDTO);

  //댓글 수정 메서드 선언
	void modify(ReplyDTO dto);
	
	//댓글 삭제 메서드 선언
	void remove(Long rno); 

  
	
}
