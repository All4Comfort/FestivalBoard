package com.whiteboard.whiteboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.whiteboard.whiteboard.repository.NoticeRepository;

@SpringBootTest
class NoticeApplicationTest {
  
  @Autowired
  private NoticeRepository noticeRepository;

  // 공지사항 db에 밀어넣기
  // @Test
  // void contextLoads() {
  //   IntStream.rangeClosed(1, 10).forEach(i -> {

  //     Notice notice = Notice.builder()
  //                     .title("제목" + i + "입니다.")
  //                     .content("이건" + i + " 번째 글입니다.")
  //                     .build();
  //     noticeRepository.save(notice);
  //   });
  // }
}
