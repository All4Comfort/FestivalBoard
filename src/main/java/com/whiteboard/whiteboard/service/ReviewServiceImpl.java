package com.whiteboard.whiteboard.service;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.entity.Review;
import com.whiteboard.whiteboard.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
 
  private final ReviewRepository reviewRepository;

  public List<Review> getAlReviews(){
    return reviewRepository.findAll();
  }

  @Override
  public Long Review(ReviewDTO reviewDTO) {
    Review review = dtoToEntity(reviewDTO, null);
    Review saveReview = reviewRepository.save(review);
    return saveReview.getReviewNum();
  }

  @Override
  public ReviewDTO getReviewById(Long reviewId) {
    Review review = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new NoSuchElementException(reviewId + "인 id 리뷰를 찾을 수 없습니다."));
    return entityToDTO(review, null, null);
  }

  @Override
  public void updateReview(Long reviewId, ReviewDTO reviewDTO) {
    // 리뷰 ID로 리뷰를 조회하고, 업데이트할 내용으로 엔티티를 수정한 후 저장
    Review existingReview = reviewRepository.findById(reviewId)
                            .orElseThrow(() -> new NoSuchElementException(reviewId +"인 id 리뷰를 찾을 수 없습니다."));
    
    // 업데이트할 내용으로 엔티티 수정
    existingReview.updateTitle(reviewDTO.getTitle());
    existingReview.updateContent(reviewDTO.getContent());

    // 수정된 엔티티를 저장
    reviewRepository.save(existingReview);
  }

  @Override
  public void deleteReview(Long reviewId) {
    // 리뷰 ID로 리뷰 삭제
    reviewRepository.deleteById(reviewId);   
  }

  @Override
  public Long saveReview(ReviewDTO reviewDTO) {
    Review review = dtoToEntity(reviewDTO, null);
    Review saveReview = reviewRepository.save(review);
    return saveReview.getId();
  }
    

}