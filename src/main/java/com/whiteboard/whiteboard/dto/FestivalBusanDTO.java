package com.whiteboard.whiteboard.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
@JsonIgnoreProperties(ignoreUnknown = true)
// 축제정보게시판 DTO
public class FestivalBusanDTO {

	private Long festivalNum; // 축제 번호

	@JsonProperty("MAIN_TITLE")
	private String festivalTitle; // 축제명

	@JsonProperty("GUGUN_NM")
	private String region; // 지역 : "부산광역시 " + "**구"

	@JsonProperty("ADDR1")
	private String venue; // 개최장소

	private String period; // 기간

	// "USAGE_DAY_WEEK_AND_TIME"키워드에 값이 있는 경우 매핑할 필드
	@JsonProperty("USAGE_DAY_WEEK_AND_TIME")
	private String firstPeriod;

	// "USAGE_DAY_WEEK_AND_TIME"키워드에 값이 없는 경우 "USAGE_DAY"키워드를 매핑할 필드
	@JsonProperty("USAGE_DAY")
	private String secondPeriod;
	/*
	 * //축제기간 setter
	 * public String setPeriod(String firstPeriod) {
	 * if (firstPeriod == null || firstPeriod.isEmpty()) {
	 * // "USAGE_DAY_WEEK_AND_TIME" 필드가 null이거나 값이 없으면 "USAGE_DAY" 필드를 가져와서 설정
	 * return getSecondPeriod();
	 * } else {
	 * return getFirstPeriod();
	 * }
	 * }
	 */
	@JsonProperty("ITEMCNTNTS")
	private String description; // 설명(묘사)

	@JsonProperty("HOMEPAGE_URL")
	private String link; // 홈페이지

	@JsonProperty("MAIN_IMG_NORMAL")
	private String poster; // 메인 포스터링크

	@JsonProperty("MAIN_IMG_THUMB")
	private String thumnail;//

	private LocalDateTime registerDate; // 작성일
	private LocalDateTime modifyDate; // 수정일

	private Long readCount; // 조회수

	// 타이틀 정보를 가져오는 getter 메서드
	public String getFestivalTitle() {
		return festivalTitle;
	}

	// 포스터 정보를 가져오는 getter 메서드
	public String getPoster() {
		return poster;
	}

	// 타이틀과, 포스터
	public void festivalTitleandPoster(String festivalTitle, String poster) {
		this.festivalTitle = festivalTitle;
		this.poster = poster;
	}
}
