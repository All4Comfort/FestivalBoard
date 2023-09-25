package com.whiteboard.whiteboard.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.PageResultDTO;
import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.entity.Review;
import com.whiteboard.whiteboard.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  public List<Review> getAlReviews() {
    return reviewRepository.findAll();
  }



  // @Override
  // public ReviewDTO getReviewByReviewNum(Long reviewNum) {

  //   Review[] reviews = reviewRepository.getReviewNum(reviewNum);

    
  //   // Review review = reviewRepository.findById(reviewNum)
  //   //     .orElseThrow(() -> new NoSuchElementException(reviewNum + "인 id 리뷰를 찾을 수 없습니다."));
  //   //     System.out.println("서비스임플에서 review엔티티 출력 : " + review);
  // }



  @Override
  public void updateReview(Long reviewId, ReviewDTO reviewDTO) {
  
  }

  @Override
  public void deleteReview(Long reviewNum) {
    // 리뷰 ID로 리뷰 삭제
    reviewRepository.deleteById(reviewNum);
  }

  @Override
  public PageResultDTO<ReviewDTO, Object[]> getReviewDTOList(PageRequestDTO pageRequestDTO) {
   
    return null;
  }



  @Override
  public List<ReviewDTO[]> getReviewDTOs() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getReviewDTOs'");
  }

  @Override
  public void modify(ReviewDTO dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'modify'");
  }

  @Override
  public ReviewDTO getReviewByReviewNum(Long reviewNum) {
    Review review = reviewRepository.findById(reviewNum)
                .orElseThrow(() -> new NoSuchElementException(reviewNum + "인 id 리뷰를 찾을 수 없습니다."));
    return entityToDTO(review);
  }




  @Override
  public Long saveReview(ReviewDTO dto) {
    Review review = dtoToEntity(dto,null);
    reviewRepository.save(review);
    return review.getReviewNum();
    //Review saveReview = reviewRepository.save(review);
    //return saveReview.getReviewNum();
  
  }


}