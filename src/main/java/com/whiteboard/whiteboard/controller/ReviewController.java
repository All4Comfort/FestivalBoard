package com.whiteboard.whiteboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.whiteboard.whiteboard.entity.Review;
import com.whiteboard.whiteboard.repository.ReviewRepository;

@Controller
public class ReviewController {

   @Autowired
    private ReviewRepository reviewRepository;

  @GetMapping("/reviewList")
    public String getReviews() {
        // 여기에 리뷰 목록을 가져오는 로직을 추가하세요.
        // 가상의 데이터를 사용하는 경우, 리스트를 생성하고 반환하면 됩니다.
        
        List<Review> reviews = new ArrayList<>();
        // 리뷰 데이터를 가져와서 reviews 리스트에 추가

        return "/reviewList";
    }

    @GetMapping("/reviewWrite")
    public String getWriteReviewPage() {
        // 리뷰 작성 페이지로 이동
        return "/reviewWrite"; // 작성할 리뷰 페이지의 경로를 반환
    }

}

