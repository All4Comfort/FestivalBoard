package com.whiteboard.whiteboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.repository.ReviewRepository;
import com.whiteboard.whiteboard.service.ReviewService;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviewList")
    public void getReviews(PageRequestDTO pageRequestDTO, Model model) {
        //model.addAttribute("result", reviewRepository.getReviewNum(1L));
        //model.addAttribute("result", reviewRepository.getReviewJoinMember(1L));
        model.addAttribute("result", reviewRepository.getReviewList());

        
        //List<Review[]> reviewList = reviewRepository.getReviewList();

        //model.addAttribute("result", reviewList);

        // 여기에 리뷰 목록을 가져오는 로직을 추가하세요.
        // 가상의 데이터를 사용하는 경우, 리스트를 생성하고 반환하면 됩니다.

        // List<Review> reviews = new ArrayList<>();
        // 리뷰 데이터를 가져와서 reviews 리스트에 추가

    }
    
    @GetMapping("/reviewDetail")
    public String getReviewDetail(@ModelAttribute ReviewDTO reviewDTO, Model model) {
        //ReviewDTO reviewDTO = reviewService.getReviewById(reviewNumLong);
        //model.addAttribute("result", reviewRepository.getReviewList());
       // model.addAttribute("result", reviewRepository.getReviewNum(1L));
        model.addAttribute("result", reviewRepository.getReferenceById(1L));
        
        return "/reviewDetail";
    }
    @GetMapping("/reviewWrite")
    public String postWriteReview(@ModelAttribute ReviewDTO reviewDTO) {
        // 리뷰 작성 페이지로 이동
        return "/reviewWrite"; // 작성할 리뷰 페이지의 경로로 리다이렉트
    }
}