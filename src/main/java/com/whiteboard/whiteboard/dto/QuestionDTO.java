package com.whiteboard.whiteboard.dto;

import java.time.LocalDateTime;

import com.whiteboard.whiteboard.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

//질문게시판DTO
public class QuestionDTO {
  
  private Long questionNum; //질문번호
  private String writer; //작성자

  
  private String title; //글제목
  private String content; //글내용
  private Boolean isSecret; //비밀글 여부
  private String state; //진행상태 : 답변미완료, 답변완료

  private LocalDateTime registerDate; //작성일
  private LocalDateTime modifyDate; //수정일
}
