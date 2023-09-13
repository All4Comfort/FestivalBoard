package com.whiteboard.whiteboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString

// 축제 정보 게시판
public class Festival extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long festivalNum; // 축제번호

	@Column(nullable = false)
	private String festivalTitle; // 축제명

	@Column(nullable = false)
	private String region; // 지역
	
	@Column(nullable = false)
	private String venue; // 개최장소

	@Column(nullable = false)
	private String period; // 기간

	@Column(nullable = false)
	private String state; // 진행상태

	@Column(nullable = false)
	private String link; // 홈페이지

	private String poster; // 포스터링크
	private Long readCount; // 조회수
	
}
