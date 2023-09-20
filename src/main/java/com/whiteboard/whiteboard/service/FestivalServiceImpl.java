package com.whiteboard.whiteboard.service;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
//@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

    //private final FestivalRepository festivalRepository;

    @Override
    @Transactional
    public void importFestivalsFromJson() {
        /* 
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
       // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);   //선언한 필드만 매핑
        
        try {

            // JSON 파일을 읽어와서 FestivalDTO 리스트로 파싱
            File jsonFile = new File("src\\main\\resources\\templates\\festivalAPI\\busanFestivalOpenAPI.json");

            //System.out.println("제이슨 파일 경로: " + jsonFile.getAbsolutePath());
            FestivalBusanDTO[] festivalDTOs = objectMapper.readValue(jsonFile, FestivalBusanDTO[].class);
            
            //System.out.println("dto배열 : "+ festivalDTOs); //dto배열 : [Lcom.whiteboard.whiteboard.dto.FestivalBusanDTO;@3549c070 인스턴스 존재함

            // FestivalDTO를 Festival 엔티티로 변환하여 저장
            for (FestivalBusanDTO busanDTO : festivalDTOs) {

                System.out.println("부산축제 API가 DTO[]에 담긴 값 : " + busanDTO); //모든값이 null

                /* 
                Festival festival = new Festival();
                festival.setFestivalTitle(busanDTO.getFestivalTitle());
                festival.setRegion("부산광역시 " + busanDTO.getRegion());
                festival.setVenue(busanDTO.getVenue());
                festival.setPeriod(busanDTO.setPeriod(busanDTO.getFirstPeriod()));
                festival.setDescription(busanDTO.getDescription());
                festival.setLink(busanDTO.getLink());
                festival.setPoster(busanDTO.getPoster());
                festival.setThumnail(busanDTO.getThumnail());
                // 필요한 필드 설정

                festivalRepository.save(festival);
                */
            }

            /*
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
                festival.setDescription(festivalJson.get("ITEMCNTNTS").asText());
                festival.setLink(festivalJson.get("HOMEPAGE_URL").asText());
                festival.setPoster(festivalJson.get("HOMEPAGE_URL").asText());
                // 필요한 필드만 추가로 설정 가능

                // 데이터베이스에 저장
                festivalRepository.save(festival);
            }
            //System.out.println("축제 데이터를 데이터베이스에 저장했습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        */
    }

