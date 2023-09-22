package com.whiteboard.whiteboard;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.whiteboard.whiteboard.entity.Review;
import com.whiteboard.whiteboard.repository.ReviewRepository;

@SpringBootTest
public class ReviewListRepositoryTest {

    @Autowired
  private ReviewRepository reviewRepository;

  @Test
   void contextLoads() {
    IntStream.rangeClosed(1, 10).forEach(i -> {
      
     Review review = Review.builder()
                     .writer(null)
                     .title("제목" + i + "입니다.")
                     .content("이건" + i + " 번째 글입니다.")
                     .goodCount(1L)
                     .build();
      reviewRepository.save(review);
     });
 } 



}
