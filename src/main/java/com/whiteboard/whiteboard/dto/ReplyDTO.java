package com.whiteboard.whiteboard.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReplyDTO {
   
   private Long replyNum; // 댓글번호
 
   private String writer; // 작성자
   private String content; // 내용
   
   private int replyLevel; //댓글수준 : 댓글(1)/대댓글(2)까지만
   private int replyStep; //댓글순서 : 댓글1/댓글1-1/댓글1-2/댓글2/댓글2-1
   
   private Long festivalNum; // 축제번호
   private Long reviewNum; // 리뷰번호
   private Long noticeNum; // 공지번호
   private Long questionNum; // 질문번호
 
   private LocalDateTime regDate; // 작성일
   private LocalDateTime modDate; // 수정일
}