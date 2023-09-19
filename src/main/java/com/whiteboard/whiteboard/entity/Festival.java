package com.whiteboard.whiteboard.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("TITLE")
	private String festivalTitle; // 축제명

	@JsonProperty("GUGUN_NM")
	private String region; // 지역
	
	@JsonProperty("MAIN_PLACE")
	private String venue; // 개최장소

	private String period; // 기간
	
	//"USAGE_DAY_WEEK_AND_TIME"키워드에 값이 있는 경우 매핑할 필드
	@JsonProperty("USAGE_DAY_WEEK_AND_TIME")
	private String firstPeriod;

	//"USAGE_DAY_WEEK_AND_TIME"키워드에 값이 없는 경우 "USAGE_DAY"키워드를 매핑할 필드
	@JsonProperty("USAGE_DAY")
	private String secondPeriod;

	//축제기간 setter
	public void setPeriod(String firstPeriod) {
    if (firstPeriod == null || firstPeriod.isEmpty()) {
        // "USAGE_DAY_WEEK_AND_TIME" 필드가 null이거나 값이 없으면 "USAGE_DAY" 필드를 가져와서 설정
        this.period = getSecondPeriod();
    } else {
        this.period = getFirstPeriod();
    }
}

	@Column(nullable = true)
	private String state; // 진행상태 : 진행예정, 진행중, 종료
	
	// 진행상태 setter
	public void setState(String state){
		//현재 날짜 기준으로 진행상태 띄우기
		// 주어진 문자열
        

        // 시작 날짜와 종료 날짜를 추출
        String[] dateParts = period.split("~");
        String startDateStr = dateParts[0].replace(".", "").trim(); // "202371"
        String endDateStr = dateParts[1].replace(".", "").trim();   // "731"

        // 시작 날짜와 종료 날짜를 LocalDate로 변환
        LocalDate startDate = LocalDate.parse(startDateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse(endDateStr, DateTimeFormatter.ofPattern("MMdd"));

        // 현재 날짜를 얻음
        LocalDate currentDate = LocalDate.now();

        // 현재 날짜와 시작 날짜, 종료 날짜를 비교하여 상태를 판단
        if (currentDate.isBefore(startDate)) {
            System.out.println("아직 시작 전입니다.");
        } else if (currentDate.isEqual(startDate) || currentDate.isBefore(endDate)) {
            System.out.println("진행 중입니다.");
        } else {
            System.out.println("이미 종료되었습니다.");
        }
	}

	@Column(length = 5000) // 2000자로 제한
	@JsonProperty("ITEMCNTNTS")
	private String description; // 설명(묘사)

	//@Column(nullable = true)
	@JsonProperty("HOMEPAGE_URL")
	private String link; // 홈페이지

	@JsonProperty("MAIN_IMG_NORMAL")
	private String poster; // 포스터링크

	@Column(name = "read_count", nullable = false, columnDefinition = "integer default 0")
	private Long readCount; // 조회수
	
}
