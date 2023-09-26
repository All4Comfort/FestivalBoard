package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.PageResultDTO;
import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.entity.Review;
import com.whiteboard.whiteboard.repository.MemberRepository;
import com.whiteboard.whiteboard.repository.ReviewRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final MemberRepository memberRepository;
  private final MemberService memberService;

  @Override
  public List<ReviewDTO> getAllReviews() {

    List<ReviewDTO> reviewDTOs = new ArrayList<>();

    List<Review> reviewList = reviewRepository.findAll();
    for (Review review : reviewList) {
      reviewDTOs.add(entityToDTO(review));
    }
    
    return reviewDTOs;
  }


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
  public void saveReview(ReviewDTO dto, HttpSession session) {
    Review review = dtoToEntity(dto, session);
    reviewRepository.save(review);
    System.out.println("글 DB에 저장 성공");
    //Review saveReview = reviewRepository.save(review);
    //return saveReview.getReviewNum();
  
  }
  
  @Override
  public void modify(ReviewDTO dto,HttpSession session) {
   Review review = dtoToEntity(dto,session);
   reviewRepository.save(review);
      
   
    
    
    
  }
  
  
  
  
  
  
  
  // Review review = reviewRepository.getReferenceById(dto.getReviewNum());
  
  // review.setContent(dto.getContent());
  // review.setTitle(dto.getTitle());
  
      // reviewRepository.save(review);
  
      
      @Override
      public ReviewDTO getReviewByReviewNum(Long reviewNum) {
        Review review = reviewRepository.findById(reviewNum)
                .orElseThrow(() -> new NoSuchElementException(reviewNum + "인 id 리뷰를 찾을 수 없습니다."));
                return entityToDTO(review); 
              }
              
              
public Review dtoToEntity(ReviewDTO reviewDTO, HttpSession session) {
  
  MemberDTO memberDTO = memberService.covertSessionToDTO(session);
  
  Member member = memberRepository.getReferenceById(memberDTO.getEmail());
  
  Review review = Review.builder()
  //리뷰 엔티티의 writer는 Member타입임!!!!!!!!!!!!!!!
  .writer(member)
  .title(reviewDTO.getTitle())
        .content(reviewDTO.getContent())
        .readCount(0L)
        .goodCount(0L)
        .build();
    return review;
  }
  
  
  
  

  
  
  
  
  
}