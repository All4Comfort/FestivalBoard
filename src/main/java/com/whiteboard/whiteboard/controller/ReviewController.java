package com.whiteboard.whiteboard.controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.ReviewDTO;
import com.whiteboard.whiteboard.repository.ReviewRepository;
import com.whiteboard.whiteboard.service.ReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"/review","","/"})
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewService reviewService;

   //     @GetMapping("/reviewList")
//     public String showReviewList(@RequestParam(name = "searchQuery", required = false) String searchQuery,
//                                  PageRequestDTO pageRequestDTO, Model model){
//     List<ReviewDTO> reviews;
//     boolean isSearch = false;

//     if(searchQuery != null && !searchQuery.isEmpty()){
//         reviews = reviewService.searchReviews(searchQuery);
//         isSearch = true;
//     }else {
//         Pageable pageable = pageRequestDTO.getPageable(Sort.by("reviewlNum").ascending());

//         Page<ReviewDTO> reviewPage = reviewService.findAllByOrderByReviewNum(pageable);
//         reviews = reviewPage.getContent();

//         int totalPages = reviewPage.getTotalPages();
        
//         int currentPage = pageRequestDTO.getPage();

//         List<Integer> pageNumbers = IntStream
//             .rangeClosed(Math.max(1, currentPage -1), Math.min(currentPage + 1, totalPages))
//             .boxed()
//             .collect(Collectors.toList());

//         boolean hasPrevPage = currentPage > 1;
//         boolean hasNextPage = currentPage < totalPages;

//         int prevPageNumber = currentPage - 1;
//         int nextPageNumber = currentPage + 1;

//         model.addAttribute("pageNumbers", pageNumbers);
//         model.addAttribute("hasPrevPage", hasPrevPage);
//         model.addAttribute("hasNextPage", hasNextPage);
//         model.addAttribute("prevPageNumber", prevPageNumber);
//         model.addAttribute("nextPageNumber", nextPageNumber);

//     }
    
//     model.addAttribute("reviews", reviews);
//     model.addAttribute("searchQuery", searchQuery);
//     model.addAttribute("isSearch", isSearch);

//     return "review/reviewlList";
//  }


    @GetMapping("/reviewList")
    public void getReviews(PageRequestDTO pageRequestDTO, Model model) {
        //model.addAttribute("result", reviewRepository.getReviewNum(1L));
        //model.addAttribute("result", reviewRepository.getReviewJoinMember(1L));
        //model.addAttribute("result", reviewRepository.getReviewList());

        model.addAttribute("result", reviewService.getAllReviews());

    }
    
    @GetMapping("/reviewDetail")
    public void getReviewDetail(@ModelAttribute ReviewDTO reviewDTO, Model model, HttpSession session) {
    
        model.addAttribute("reviewDTO", reviewService.getReviewByReviewNum(reviewDTO.getReviewNum()));
    }

    //글쓰기 에디터 사용 시 자동 삽입되는 html 태그 제거하는 메서드
    public static String removeHtmlTags(String input) {
        if (input == null) {
            return "";
        }
        // 정규 표현식을 사용하여 HTML 태그를 제거하고 기호를 대체합니다.
        String regex = "<[^>]*>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("").replace("&lt;", "<").replace("&gt;", ">"); 
    }

    @PostMapping("/reviewWrite")
    public String saveReivew(@ModelAttribute ReviewDTO dto, RedirectAttributes attributes, HttpSession session){

        System.out.println("작성한 값 담은 dto 전달되는지 확인!! : " + dto);
        
        //내용에서 html태그 제거하고 DB에 저장하도록!
        dto.setContent(removeHtmlTags(dto.getContent()));

        reviewService.saveReview(dto, session);
        //attributes.addFlashAttribute("newReviewNum", newReviewNum);
        return "redirect:/review/reviewList";
    }

    @GetMapping("/reviewWrite")
    public void postWriteReview() {
        
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("reviewNum") Long reviewNum, RedirectAttributes redirect){
        System.out.println("GGGGGGGG");
        reviewService.remove(reviewNum);
        redirect.addAttribute("reviewDTO",reviewNum);
        return "redirect:/review/reviewList";
    }
    
       @PostMapping("/reviewModify")
    public String modify(@ModelAttribute ReviewDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirect){
        
        
        reviewService.modify(dto);
        
        redirect.addAttribute("dto", dto);
        return "redirect:/review/reviewDetail";
    }

    // @PostMapping("/reviewModify")
    // public String modify(ReviewDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirect){
    //     reviewService.modify(dto);

    //     redirect.addAttribute("reviewDTO", dto);
    //     return "redirect:/review/reviewDetail";
    // }

    @GetMapping("/reviewModify")
    public void reviewModify(@ModelAttribute ReviewDTO dto, Model model){
    System.out.println("============================================================");
        System.out.println("수정페이지에서 DTO!!!!!!!!!!!!!!!!!!!!! : " + dto); //reviewNum, title, content옴
        model.addAttribute("dto", dto);
    }

    
 








    // @PostMapping("/reviewDetail")
    // public String getReviewDetail(@ModelAttribute ReviewDTO reviewDTO, Model model, HttpSession session) {
    //     //ReviewDTO reviewDTO = reviewService.getReviewById(reviewNumLong);
    //     //model.addAttribute("result", reviewRepository.getReviewList());
    //    // model.addAttribute("result", reviewRepository.getReviewNum(1L));
    //    // model.addAttribute("result", reviewRepository.getReviewNum(reviewDTO.getReviewNum()));
    //      model.addAttribute("reviewDTO", reviewService.getReviewByReviewNum(reviewDTO.getReviewNum()));
    //      return "redirect:/review/reviewDetail";
    // }

  

}