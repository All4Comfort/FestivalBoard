package com.whiteboard.whiteboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.repository.ReviewRepository;
import com.whiteboard.whiteboard.service.ReviewService;

@Controller
@RequestMapping({"/review","","/"})
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviewList")
    public void getReviews(PageRequestDTO pageRequestDTO, Model model) {
        //model.addAttribute("result", reviewRepository.getReviewNum(1L));
        //model.addAttribute("result", reviewRepository.getReviewJoinMember(1L));
        //model.addAttribute("result", reviewRepository.getReviewList());

        model.addAttribute("result", reviewService.getAllReviews());
        
        //List<Review[]> reviewList = reviewRepository.getReviewList();

        //model.addAttribute("result", reviewList);

        // 여기에 리뷰 목록을 가져오는 로직을 추가하세요.
        // 가상의 데이터를 사용하는 경우, 리스트를 생성하고 반환하면 됩니다.

        // List<Review> reviews = new ArrayList<>();
        // 리뷰 데이터를 가져와서 reviews 리스트에 추가

    }
    
    @GetMapping("/reviewDetail")
    public void getReviewDetail(@ModelAttribute ReviewDTO reviewDTO, Model model) {
        //ReviewDTO reviewDTO = reviewService.getReviewById(reviewNumLong);
        //model.addAttribute("result", reviewRepository.getReviewList());
       // model.addAttribute("result", reviewRepository.getReviewNum(1L));
       // model.addAttribute("result", reviewRepository.getReviewNum(reviewDTO.getReviewNum()));

        model.addAttribute("reviewDTO", reviewService.getReviewByReviewNum(reviewDTO.getReviewNum()));
    }

    @PostMapping("/reviewWrite")
    public String saveReivew(@ModelAttribute ReviewDTO dto, RedirectAttributes attributes){
        Long newReviewNum = reviewService.saveReview(dto);
        attributes.addFlashAttribute("newReviewNum", newReviewNum);
        return "redirect:/review/reviewList";
    }

    @GetMapping("/reviewWrite")
    public void postWriteReview() {
        
    }
}