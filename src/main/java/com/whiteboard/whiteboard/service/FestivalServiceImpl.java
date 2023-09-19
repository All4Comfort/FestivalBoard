package com.whiteboard.whiteboard.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    private final FestivalRepository festivalRepository;

    @Override
    @Transactional
    public void importFestivalsFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            // JSON 파일을 읽어와서 JsonNode로 파싱
            JsonNode jsonNode = objectMapper.readTree(new File(
                "C:\\Users\\Manic-063\\Documents\\GitHub\\web\\whiteboard\\src\\main\\resources\\templates\\festivalAPI\\busanFestivalOpenAPI.json"));

            // JSON 데이터를 필요한 필드만 선택하여 엔티티로 매핑
            for (JsonNode festivalJson : jsonNode) {
                // 필요한 필드만 추출하여 Festival 엔티티 객체 생성
                Festival festival = new Festival();
                festival.setFestivalTitle(festivalJson.get("TITLE").asText());
                festival.setRegion(festivalJson.get("GUGUN_NM").asText());
                festival.setVenue("부산광역시: " + festivalJson.get("MAIN_PLACE").asText());
                festival.setFirstPeriod(festivalJson.get("USAGE_DAY_WEEK_AND_TIME").asText());
                festival.setSecondPeriod(festivalJson.get("USAGE_DAY").asText());
                //festival.setState(festivalJson.get("USAGE_DAY").asText());
                festival.setDescription(festivalJson.get("ITEMCNTNTS").asText());
                festival.setLink(festivalJson.get("HOMEPAGE_URL").asText());
                festival.setPoster(festivalJson.get("HOMEPAGE_URL").asText());
                // 필요한 필드만 추가로 설정 가능

                // 데이터베이스에 저장
                festivalRepository.save(festival);
            }

            System.out.println("축제 데이터를 데이터베이스에 저장했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
