package com.whiteboard.whiteboard;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whiteboard.whiteboard.entity.Festival;
import com.whiteboard.whiteboard.repository.FestivalRepository;
import com.whiteboard.whiteboard.service.FestivalService;

@SpringBootTest
public class FestivalRepositoryTest {

    @Autowired
    private FestivalService festivalService;

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper 주입

    // @MockBean
    // private FestivalRepository festivalRepository;

    // @Test
    // public void testSaveFestivalEntity() {
    // Festival festival = Festival.builder()
    // .festivalTitle("부산바다축제")
    // .region("부산광역시 수영구")
    // .venue("부산시내 주요 해수욕장")
    // .period("2023.8.1.(화) ~ 8.6.(일)")
    // .state("진행예정")
    // .description(
    // "<p class=\"font-size28 colorDarkBlue medium\">2023년 제27회
    // 부산바다축제</p>2023.8.1.(화) ~ 8.6.(일) / 해운대 해수욕장, 다대포 해수욕장 일원\n전국 최대 규모 힙한 풀파티인
    // 'Night Pool Party'와 야심차게 선보이는 '원더풀 컬러풀'을 중심으로 여러 연계행사 및 부대행사를 만나보실 수
    // 있습니다.\n\n[해운대 해수욕장]\n◆ Night Pool Party ~ 전국 최대 규모 나이트 풀 파티 \n 2023.8.4.(금)
    // 19:00~22:00 해운대 해수욕장 특설무대\n\n[다대포 해수욕장]\n◆ 원더풀 콘서트 ~ 폴킴, 소란, 제이레빔 여름 감성
    // 플레이리스트! \n 2023.8.5.(토) 18:20~20:10 \n◆ 컬러풀 불꽃쇼 ~ 눈부신 노을아래 펼쳐지는 마법 같은 순간 \n
    // 2023.8.5.(토) 20:10~20:30 \n \n[연계행사] 대대포해수욕장 일원 \nº 부산줌바다 2023.8.1.(화)
    // 18:30~21:00 \nº 해변살사댄스 페스티벌 2023.8.2.(수) 18:00~21:30\nº 장애인 한바다축제
    // 2023.8.3.(목) 15:00~18:00 \nº 열린바다 열린음악회 2023.8.3.(목) 19:00~21:00 \nº 다대포 포크樂
    // 콘서트 2023.8.4.(금) 19:30~21:00\n\n[부대행사](유료) 다대포 해수욕장 일원 \nº 힐링 서핑 체험
    // 2023.8.1.(화)~8.6.(일) *8.5.(토) 제외 / 1차 17:00~19:30, 2차 17:30~20:00\nº 선셋 서핑
    // 2023.8.1.(화)~8.6.(일) 16:30~20:30\nº 비치코밍 2023.8.4.(금)~8.6.(일) 10:00~12:00 \nº
    // 비치 요가 2023.8.1.(화)~8.2.(수), 8.6.(일) / 1회차 17:00~18:00, 2회차 19:00~20:00\nº 버스킹
    // 2023.8.2.(수)~8.4.(금) / 1회차 16:00~16:30, 2회차 17:00~17:30 \n(부대시설)\nº 푸드코트 &
    // 비치펍 2023.8.1.(화)~8.4.(금) 16:00~21:00, 2023.8.5.(토) 14:00~21:00, 2023.8.6.(일)
    // 16:00~21:00 \nº 푸드라운지 2023.8.1.(화)~8.4.(금) 상시 운영\n ※ 주최 측의 사정에 따라 내용 및 일정은
    // 변동될 수 있습니다\n타오르는 태양, 시원한 바다, 금빛 백사장, 화려한 네온사인, 신나는 음악!\n이 모든 것을 한 번에 즐길 수 있는
    // 부산의 대표 여름축제, 부산바다축제를 소개한다.\n부산바다축제는 해마다 여름이 되면 해운대, 광안리, 다대포, 송도, 송정해수욕장 등 부산
    // 시내 해수욕장에서 열린다. \n축제는 각 해수욕장의 색깔을 담은 다양한 프로그램으로 사람들을 맞이하는데 축제의 시작을 여는 것은 해운대에서
    // 펼쳐지는 개막파티다. 매해 새로운 모습을 추구하며 대규모 살수시설 및 버블폼, 인공 풀장을 활용한 '풀파티' 컨셉의 올 스탠딩 참여
    // 공연으로 시작한다.\n개막파티를 놓친 이들이라도 매일 새롭게 열리는 참여행사를 통해 바다축제를 즐길 수 있는데, 해운대 구남로 일원에서
    // 펼쳐지는 물의 난장 행사가 그 중 하나이다. 미니풀장과 물총을 이용한 워터 카니발 컨셉의 이 행사는 밴드공연팀, EDM 공연 및 다양한
    // 이벤트와 함께 개최되는 프로그램이다.\n밤바다를 열정적으로 즐기고 싶은 이들을 위한 디제잉의 향연도 펼쳐진다. 국내에 거주하는 외국인
    // DJ들이 펼치는 디제잉 경연대회는 주체할 수 없는 에너지를 맘껏 표출할 수 있는 신나는 젊음의 장이다.\n뜨거운 여름밤을 달구기에 춤만 한
    // 것도 없다, 해변에서 펼쳐지는 댄스파티 현장은 보는 것만으로도 몸치에서 탈출할 수 있을 것만 같다. 국‧내외 댄스 동호인들의 열정적인
    // 스윙, 살사, 줌바 댄스를 직접 따라하다 보면 몸이 저절로 박자를 즐기고 있다.\n다양하고 신나는 공연은 계속 이어진다.\n부산 바다의
    // 조용한 정취를 좋아하는 이들에게는 열린바다 열린음악회를, 미래의 힙합 전사들에게는 줌바다와 해변살사댄스 페스티벌을, 다대포 낙조의 낭만을
    // 경험하고자 한다면 다대포 포크樂 페스티벌을 추천한다. 최고의 뮤지션들이 밤바다를 배경으로 연주하는 음악과 그들의 노랫소리는 귀를
    // 호강시켜주기에 충분하니 말이다.\n더불어 건전한 장애인 놀이문화와 다양한 스포츠행사, 레크레이션이 진행되는 장애인 한바다축제, 부산 청소년
    // 바다축제도 열려 남녀노소, 장애인‧비장애인 할 것 없이 누구나 즐길 수 있는 부산바다축제임을 보여준다.\n부산의 바다가 간직한 매력은
    // 어디까지일까.\n밤의 풍경, 음악, 젊음, 열정.\n부산의 여름을 생각했을 때 떠오르는 많은 말들이 있지만 부산바다축제야말로 그 모든 것을
    // 다 담고 있는 종합선물세트라고 할 수 있을 것이다.\n\n무엇을 생각하든 그 이상을 만날 수 있는 축제.\n이 축제만으로 여름의 부산을
    // 찾을 이유는 충분하지 않을까.\n\n")
    // .link("http://www.bfo.or.kr")
    // .poster("https://www.visitbusan.net/uploadImgs/files/cntnts/20191213191711585_ttiel")
    // .readCount(0L)
    // .build();

    // festivalRepository.save(festival); // 데이터베이스에 저장

    // System.err.println(festival);

    // // 저장한 엔티티를 다시 조회하여 확인
    // Festival savedFestival =
    // festivalRepository.findById(festival.getFestivalNum()).orElse(null);
    // // assertThat을 사용하여 테스트 검증
    // Assertions.assertThat(savedFestival).isNotNull();
    // Assertions.assertThat(savedFestival.getFestivalTitle()).isEqualTo("부산바다축제");
    // }

    @Test
    public void testImportFestivalsFromJson() {
        try {
            // JSON 파일을 클래스 경로에서 읽어옴
            ClassPathResource resource = new ClassPathResource("templates/festivalAPI/busanFestivalOpenAPI.json");

            InputStream inputStream = resource.getInputStream();
            // ObjectMapper를 사용하여 JSON 데이터 파싱
            JsonNode jsonNode = objectMapper.readTree(inputStream);

            // 필드 가져오기 및 널 체크
            String festivalTitle = getValueFromJson(jsonNode, "/getFestivalKr/item/0/TITLE");
            String region = getValueFromJson(jsonNode, "/getFestivalKr/item/0/GUGUN_NM");
            String venue = getValueFromJson(jsonNode, "/getFestivalKr/item/0/MAIN_PLACE");
            String period = getValueFromJson(jsonNode, "/getFestivalKr/item/0/USAGE_DAY_WEEK_AND_TIME");
            String state = getValueFromJson(jsonNode, "/getFestivalKr/item/0/USAGE_DAY");
            String description = getValueFromJson(jsonNode, "/getFestivalKr/item/0/ITEMCNTNTS");
            String link = getValueFromJson(jsonNode, "/getFestivalKr/item/0/HOMEPAGE_URL");
            String poster = getValueFromJson(jsonNode, "/getFestivalKr/item/0/MAIN_IMG_NORMAL");

            // 필드 값을 이용하여 Festival 엔티티 생성 및 저장
            Festival festival = Festival.builder()
                    .festivalTitle(festivalTitle)
                    .region(region)
                    .venue(venue)
                    .period(period)
                    .state(state)
                    .description(description)
                    .link(link)
                    .poster(poster)
                    .readCount(0L)
                    .build();

            festivalRepository.save(festival); // 데이터베이스에 저장

            // 저장한 엔티티를 다시 조회하여 확인
            Festival savedFestival = festivalRepository.findById(festival.getFestivalNum()).orElse(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getValueFromJson(JsonNode jsonNode, String fieldPath) {
        JsonNode fieldNode = jsonNode.at(fieldPath);
        return fieldNode.isMissingNode() ? "" : fieldNode.asText();
    }

}
