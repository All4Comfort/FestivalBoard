package com.whiteboard.whiteboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteboard.whiteboard.repository.FestivalRepository;

@SpringBootTest
public class FestivalRepositoryTest {

    // @Autowired
    // private FestivalService festivalService;

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper 주입

    // @Test
    // public void insertFestivalAPI() {
    // festivalService.importFestivalsFromJson();
    // }

    // @MockBean
    // private FestivalRepository festivalRepository;

    // System.err.println(festival);

    // // 저장한 엔티티를 다시 조회하여 확인
    // Festival savedFestival =
    // festivalRepository.findById(festival.getFestivalNum()).orElse(null);
    // // assertThat을 사용하여 테스트 검증
    // Assertions.assertThat(savedFestival).isNotNull();
    // Assertions.assertThat(savedFestival.getFestivalTitle()).isEqualTo("부산바다축제");

    // @Test
    // public void testImportFestivalsFromJson() {
    //     try {
    //         // JSON 파일을 클래스 경로에서 읽어옴
    //         ClassPathResource resource = new ClassPathResource("templates/festivalAPI/busanFestivalOpenAPI.json");

    //         InputStream inputStream = resource.getInputStream();
    //         // ObjectMapper를 사용하여 JSON 데이터 파싱
    //         JsonNode jsonNode = objectMapper.readTree(inputStream);

    //         // "item" 배열에 있는 모든 아이템을 가져옴
    //         JsonNode items = jsonNode.at("/getFestivalKr/item");

    //         // 모든 아이템을 처리
    //         for (JsonNode item : items) {
    //             // 필드 가져오기 및 널 체크
    //             String festivalTitle = getValueFromJson(item, "MAIN_TITLE");
    //             String region = "부산광역시 " + getValueFromJson(item, "GUGUN_NM");
    //             String venue = getValueFromJson(item, "ADDR1");
    //             // String period = getValueFromJson(item, "USAGE_DAY_WEEK_AND_TIME");
    //             String firstPeriod = getValueFromJson(item, "USAGE_DAY_WEEK_AND_TIME");
    //             String secondPeriod = getValueFromJson(item, "USAGE_DAY");
    //             String description = getValueFromJson(item, "ITEMCNTNTS");
    //             String link = getValueFromJson(item, "HOMEPAGE_URL");
    //             String poster = getValueFromJson(item, "MAIN_IMG_NORMAL");

    //             String thumbnail = getValueFromJson(item, "MAIN_IMG_THUMB");

    //             // FestivalBusanDTO 생성
    //             FestivalBusanDTO festivalDTO = FestivalBusanDTO.builder()
    //                     .festivalTitle(festivalTitle)
    //                     .region(region)
    //                     .venue(venue)
    //                     // .period(period)
    //                     .firstPeriod(firstPeriod)
    //                     .secondPeriod(secondPeriod)
    //                     .description(description)
    //                     .link(link)
    //                     .poster(poster)
    //                     .thumnail(thumbnail)
    //                     .readCount(0L)
    //                     .build();

    //             // DTO를 엔티티로 변환하여 저장
    //             Festival festival = convertDtoToEntity(festivalDTO);

    //             // 진행상태 설정
    //             // festival.setState();

    //             // Festival 엔티티를 저장

    //             String thumnail = getValueFromJson(item, "MAIN_IMG_THUMB");

    //             // JSON 파일 DTO에 담기
    //             FestivalBusanDTO dto = new FestivalBusanDTO();
    //             dto.setFestivalTitle(festivalTitle);
    //             dto.setRegion(region);
    //             dto.setVenue(venue);
    //             dto.setDescription(description);
    //             dto.setLink(link);
    //             dto.setPoster(poster);
    //             dto.setThumnail(thumnail);
    //             // period 값 세팅하기
    //             if (firstPeriod != "") {

    //             } else {

    //             }
    //             dto.setFirstPeriod(firstPeriod);
    //             dto.setSecondPeriod(secondPeriod);
    //             dto.setPeriod(dto.getFirstPeriod());

    //             // System.out.println("부산축제 기간 : " + dto.getPeriod()); //모든값이 null

    //             // DTO 에서 ENTITY로 변환
    //             Festival festival = Festival.builder()
    //                     .festivalTitle(dto.getFestivalTitle())
    //                     .region("부산광역시 " + dto.getRegion())
    //                     .venue(dto.getVenue())
    //                     .period(dto.getPeriod())
    //                     .description(dto.getDescription())
    //                     .link(dto.getLink())
    //                     .poster(dto.getPoster())
    //                     .thumnail(dto.getThumnail())
    //                     .readCount(0L)
    //                     .build();

    //             /*
    //              * 
    //              * Festival festival = new Festival();
    //              * festival.setFestivalTitle(dto.getFestivalTitle());
    //              * festival.setRegion("부산광역시 " + dto.getRegion());
    //              * festival.setVenue(dto.getVenue());
    //              * festival.setPeriod(dto.getPeriod());
    //              * festival.setDescription(dto.getDescription());
    //              * festival.setLink(dto.getLink());
    //              * festival.setPoster(dto.getPoster());
    //              * festival.setThumnail(dto.getThumnail());
    //              * festival.setReadCount(0L);
    //              */

    //             festivalRepository.save(festival);
    //         }

    //         /*
    //          * // 필드 값을 이용하여 Festival 엔티티 생성 및 저장
    //          * Festival festival = Festival.builder()
    //          * .festivalTitle(festivalTitle)
    //          * .region("부산광역시 " + region)
    //          * .venue(venue)
    //          * .period(period)
    //          * .description(description)
    //          * .link(link)
    //          * .poster(poster)
    //          * .readCount(0L)
    //          * .build();
    //          */

    //         // festivalRepository.save(festival); // 데이터베이스에 저장
    //         // }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // // getValueFromJson 메서드는 주어진 JsonNode 객체에서 특정 필드의 값을 가져오는 데 사용되는 보조 메서드입니다. 이
    // // 메서드는 JSON 데이터에서 필드 값을 가져오는 과정을 단순화하고 널 값 검사를 수행하기 위해 작성되었습니다.

    // private String getValueFromJson(JsonNode jsonNode, String fieldName) {
    //     JsonNode fieldNode = jsonNode.get(fieldName);
    //     return fieldNode != null ? fieldNode.asText() : "";
    // }

    // // FestivalBusanDTO를 Festival 엔티티로 변환하는 메서드
    // private Festival convertDtoToEntity(FestivalBusanDTO festivalDTO) {
    //     Festival festival = Festival.builder()
    //             .festivalTitle(festivalDTO.getFestivalTitle())
    //             .region(festivalDTO.getRegion())
    //             .venue(festivalDTO.getVenue())
    //             .period(festivalDTO.getPeriod())
    //             .description(festivalDTO.getDescription())
    //             .link(festivalDTO.getLink())
    //             .poster(festivalDTO.getPoster())
    //             .readCount(festivalDTO.getReadCount())
    //             .build();

    //     return festival;
    // }

}
