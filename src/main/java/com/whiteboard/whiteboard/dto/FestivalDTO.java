package com.whiteboard.whiteboard.dto;

import java.time.LocalDateTime;

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

// 축제정보게시판 DTO
public class FestivalDTO {

	private Long festivalNum; // 축제 번호
	private String festivalTitle; // 축제명
	private String region; // 지역
	private String venue; // 개최장소
	private String period; // 기간
	private String state; // 진행상태
	private String link; // 홈페이지
	private String poster; // 포스터링크
	private LocalDateTime registerDate; // 작성일
	private LocalDateTime modifyDate; // 수정일
	private Long readCount; // 조회수
	
	
}
