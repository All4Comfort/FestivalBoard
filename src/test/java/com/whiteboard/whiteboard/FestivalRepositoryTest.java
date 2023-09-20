package com.whiteboard.whiteboard;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteboard.whiteboard.dto.forAPI.DaejeonDTO;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

@SpringBootTest
public class FestivalRepositoryTest {

    // @Autowired
    // private FestivalService festivalService;

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper 주입

    @Test
    public void testImportFestivalsFromJson() {
        try {
            // JSON 파일을 클래스 경로에서 읽어옴
            ClassPathResource resource = new ClassPathResource(
                    "templates/festivalAPI/daejeonFestivalOpenAPI.json");

            InputStream inputStream = resource.getInputStream();
            // ObjectMapper를 사용하여 JSON 데이터 파싱
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // "item" 배열에 있는 모든 아이템을 가져옴
            JsonNode items = jsonNode.at("/getFestivalKr/item");

            // 모든 아이템을 처리
            for (JsonNode item : items) {
                // 필드 가져오기 및 널 체크
                String festivalTitle = getValueFromJson(item, "festvNm");
                String region = getValueFromJson(item, "festvAddr");

                // festvAddr 필드 값에서 "~구"까지만 추출하기 ex) 대전 중구
                int index = region.indexOf("구");
                if (index != -1) {
                    region = region.substring(0, index + 1); // '구'를 포함하여 추출
                    System.out.println(region);
                }

                String period = getValueFromJson(item, "festvPrid");
                String description = getValueFromJson(item, "festvSumm");
                String link = getValueFromJson(item, "hmpgAddr");
                String venue = "";
                String venue1 = getValueFromJson(item, "festvPlcNm");
                String venue2 = getValueFromJson(item, "festvAddr");

                if (venue1 != "") {
                    venue = venue1;
                } else {
                    venue = venue2;
                }

                // FestivalBusanDTO 생성
                DaejeonDTO dto = DaejeonDTO.builder()
                        .festivalTitle(festivalTitle)
                        .region(region)
                        .venue(venue)
                        .period(period)
                        .description(description)
                        .link(link)
                        .readCount(0L)
                        .build();

                System.err.println("대전축제 장소 : " + dto.getVenue());

                // DTO를 엔티티로 변환하여 저장
                Festival festival = convertDtoToEntity(dto);

                festivalRepository.save(festival);
            } // for문 끝

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getValueFromJson 메서드는 주어진 JsonNode 객체에서 특정 필드의 값을 가져오는 데 사용되는 보조 메서드입니다. 이
    // 메서드는 JSON 데이터에서 필드 값을 가져오는 과정을 단순화하고 널 값 검사를 수행하기 위해 작성되었습니다.

    private String getValueFromJson(JsonNode jsonNode, String fieldName) {
        JsonNode fieldNode = jsonNode.get(fieldName);
        return fieldNode != null ? fieldNode.asText() : "";
    }

    // FestivalBusanDTO를 Festival 엔티티로 변환하는 메서드
    private Festival convertDtoToEntity(DaejeonDTO festivalDTO) {
        Festival festival = Festival.builder()
                .festivalTitle(festivalDTO.getFestivalTitle())
                .region(festivalDTO.getRegion())
                .venue(festivalDTO.getVenue())
                .period(festivalDTO.getPeriod())
                .description(festivalDTO.getDescription())
                .link(festivalDTO.getLink())
                .readCount(festivalDTO.getReadCount())
                .build();

        return festival;
    }

}
