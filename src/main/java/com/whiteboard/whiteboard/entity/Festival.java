package com.whiteboard.whiteboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
// 축제 정보 게시판
public class Festival extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long festivalNum; // 축제번호

	@Column(nullable = false, name = "festival_title")
	//@JsonProperty("TITLE")
	private String festivalTitle; // 축제명

	//@JsonProperty("GUGUN_NM")
	private String region; // 지역
	
	//@JsonProperty("MAIN_PLACE")
	private String venue; // 개최장소

	private String period; // 기간
	
	//포기....ㅎㅎ
	//@Column(nullable = true)
	//private String state; // 진행상태 : 진행예정, 진행중, 종료
	

	@Column(length = 5000) // 2000자로 제한
	//@JsonProperty("ITEMCNTNTS")
	private String description; // 설명(묘사)

	//@Column(nullable = true)
	//@JsonProperty("HOMEPAGE_URL")
	private String link; // 홈페이지

	//@JsonProperty("MAIN_IMG_NORMAL")
	private String poster; // 포스터링크

	private String thumnail;

	@Column(name = "read_count", nullable = false, columnDefinition = "integer default 0")
	private Long readCount; // 조회수
	
}
