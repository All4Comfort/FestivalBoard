package com.whiteboard.whiteboard.dto.forAPI;

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
public class BusanDTO {

	private Long festivalNum; // 축제 번호

	@JsonProperty("MAIN_TITLE")
	private String festivalTitle; // 축제명

	@JsonProperty("GUGUN_NM")
	private String region; // 지역 : "부산광역시 " + "**구"

	@JsonProperty("ADDR1")
	private String venue; // 개최장소

	private String period; // 기간

		//"USAGE_DAY_WEEK_AND_TIME"키워드에 값이 있는 경우 매핑할 필드
		@JsonProperty("USAGE_DAY_WEEK_AND_TIME")
		private String firstPeriod;
	
		//"USAGE_DAY_WEEK_AND_TIME"키워드에 값이 없는 경우 "USAGE_DAY"키워드를 매핑할 필드
		@JsonProperty("USAGE_DAY")
		private String secondPeriod;
	
	@JsonProperty("ITEMCNTNTS")
	private String description; // 설명(묘사)

	@JsonProperty("HOMEPAGE_URL")
	private String link; // 홈페이지

	@JsonProperty("MAIN_IMG_NORMAL")
	private String poster; // 포스터링크

	@JsonProperty("MAIN_IMG_THUMB")
	private String thumnail;

	private LocalDateTime registerDate; // 작성일
	private LocalDateTime modifyDate; // 수정일

	private Long readCount; // 조회수
	
	/*
	 * test 코드
	 * 
	 * // JSON 파일을 클래스 경로에서 읽어옴
            ClassPathResource resource = new ClassPathResource("templates/festivalAPI/busanFestivalOpenAPI.json");

            InputStream inputStream = resource.getInputStream();
            // ObjectMapper를 사용하여 JSON 데이터 파싱
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // "item" 배열에 있는 모든 아이템을 가져옴
            JsonNode items = jsonNode.at("/getFestivalKr/item");

						// 모든 아이템을 처리
	 * for (JsonNode item : items) {
                // 필드 가져오기 및 널 체크
                String festivalTitle = getValueFromJson(item, "MAIN_TITLE");

                // MAIN_TITLE 필드의 값 가공 : "(한,영, 중간,중번,일)" 부분 잘라내기
                // 정규식을 사용하여 괄호와 괄호 안의 내용 제거
                festivalTitle = festivalTitle.replaceAll("\\([^)]*\\)", "").trim();

                String region = getValueFromJson(item, "GUGUN_NM");
                String venue = getValueFromJson(item, "ADDR1");
								String period = "";
                String firstPeriod = getValueFromJson(item, "USAGE_DAY_WEEK_AND_TIME");
                String secondPeriod = getValueFromJson(item, "USAGE_DAY");
                String description = getValueFromJson(item, "ITEMCNTNTS");
                String link = getValueFromJson(item, "HOMEPAGE_URL");
                String poster = getValueFromJson(item, "MAIN_IMG_NORMAL");
                String thumnail = getValueFromJson(item, "MAIN_IMG_THUMB");

								if (firstPeriod != "") {
                    return period = firstPeriod;
                } else {
                    return period = secondPeriod;
                }

                // JSON 파일 DTO에 담기
                BusanDTO dto = BusanDTO.builder()
                        .festivalTitle(festivalTitle)
                        .region(region)
                        .venue(venue)
                        .period(period)
                        .description(description)
                        .link(link)
                        .poster(poster)
                        .thumnail(thumbnail)
                        .readCount(0L)
                        .build();

								// DTO를 엔티티로 변환하여 저장
                Festival festival = convertDtoToEntity(festivalDTO);

                festivalRepository.save(festival);
            }
						 } catch (IOException e) {
            e.printStackTrace();
        }
    }

						// FestivalBusanDTO를 Festival 엔티티로 변환하는 메서드
    private Festival convertDtoToEntity(FestivalBusanDTO festivalDTO) {
        Festival festival = Festival.builder()
                .festivalTitle(festivalDTO.getFestivalTitle())
                .region(festivalDTO.getRegion())
                .venue(festivalDTO.getVenue())
                .period(festivalDTO.getPeriod())
                .description(festivalDTO.getDescription())
                .link(festivalDTO.getLink())
                .poster(festivalDTO.getPoster())
                .readCount(festivalDTO.getReadCount())
                .build();

        return festival;
    }
	 * 
	 */

}
