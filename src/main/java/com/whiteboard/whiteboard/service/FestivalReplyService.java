package com.whiteboard.whiteboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.ReplyDTO;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.entity.FestivalReply;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalReplyService {

  public final MemberRepository memberRepository;

  public List<ReplyDTO> getAllReply(Long festivalNum) {
        return null;
  }

  public List<ReplyDTO> register(ReplyDTO replyDTO) {
        return null;
  }

  public void modify(ReplyDTO dto) {

  }

  public void remove(Long rno) {
    
  }
  //DTO를 Entity로 변환하는 메서드 선언
	public FestivalReply dtoToEntity(ReplyDTO replyDTO) {
		
		//글번호가 필요하니 Festival Entity를 참조해야 한다.
    Festival festival = Festival.builder().festivalNum(replyDTO.getFestivalNum()).build();
		
		Member member = memberRepository.getReferenceById(replyDTO.getWriter());

    FestivalReply festivalReply = FestivalReply.builder()
															.from(festival)
															.content(replyDTO.getContent())
															.replyLevel(replyDTO.getReplyLevel())
															.replyStep(replyDTO.getReplyStep())
															.writer(member)
															.build();
		
		return festivalReply;
	}
	
	//Entity를 DTO로 변환하는 메서드 선언
	public ReplyDTO entityToDTO(FestivalReply reply) {
		
		ReplyDTO dto = ReplyDTO.builder()
					    .writer(reply.getWriter().getEmail())
              .content(reply.getContent())
              .replyLevel(reply.getReplyLevel())
              .replyStep(reply.getReplyStep())
              .festivalNum(reply.getFrom().getFestivalNum())
              .regDate(reply.getRegisterDate())
              .modDate(reply.getModifyDate())
					    .build();
		
		return dto;
	}
}
