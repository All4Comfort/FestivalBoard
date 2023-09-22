package com.whiteboard.whiteboard.service;

import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.entity.Review;

public interface ReviewService {
  // 새로운 리뷰 생성
  Long Review(ReviewDTO reviewDTO);

  // 리뷰의 ID로 리뷰 가져오기
  ReviewDTO getReviewById(Long reviewId);

  // 기존 리뷰 업데이트
  void updateReview(Long reviewId, ReviewDTO reviewDTO);

  // 리뷰의 ID로 리뷰 삭제
  void deleteReview(Long reviewId);

  default ReviewDTO entityToDTO(Review review, ReviewDTO reviewDTO, Long replycount) {
    ReviewDTO dto = ReviewDTO.builder()
        .writer(reviewDTO.getWriter())
        .title(reviewDTO.getTitle())
        .content(reviewDTO.getContent())
        .readCount(0L)
        .goodCount(0L)
        .festivalNum1(reviewDTO.getFestivalNum1())
        .festivalNum2(reviewDTO.getFestivalNum2())
        .festivalNum3(reviewDTO.getFestivalNum3())
        .festivalNum4(reviewDTO.getFestivalNum4())
        .festivalNum5(reviewDTO.getFestivalNum5())
        .hashTag1(reviewDTO.getHashTag1())
        .hashTag2(reviewDTO.getHashTag2())
        .hashTag3(reviewDTO.getHashTag3())
        .hashTag4(reviewDTO.getHashTag4())
        .hashTag5(reviewDTO.getHashTag5())
        .build();
    return dto;
  }

  default Review dtoToEntity(ReviewDTO reviewDTO, Member member) {
    Member mem = Member.builder()
        .email(member.getEmail())
        .build();

    Review review = Review.builder()
        .writer(member)
        .title(reviewDTO.getTitle())
        .content(reviewDTO.getContent())
        .readCount(0L)
        .goodCount(0L)
        .build();
    return review;
  }

  // Long saveReview(ReviewDTO reviewDTO);
}