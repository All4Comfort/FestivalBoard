package com.whiteboard.whiteboard.controller;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


//  @GetMapping("/reviewList")
//     public String review(
//             @RequestParam(name = "searchQuery", required = false) String searchQuery,
//             PageRequestDTO pageRequestDTO, Model model) {

//         List<ReviewDTO> reviews; // 축제 목록을 담을 변수
//         boolean isSearch = false; // 검색 여부를 나타내는 변수

//         if (searchQuery != null && !searchQuery.isEmpty()) {
//             // 검색어가 제공된 경우, 해당 검색어를 사용하여 축제를 검색합니다.
//             reviews = reviewService.searchReviews(searchQuery); // 검색된 축제 목록을 가져옴
//             isSearch = true; // 검색 여부 플래그를 true로 설정
//         } else {
//             // 검색어가 없는 경우, 페이징을 위한 로직을 수행합니다.

//             // 페이지 요청 정보(pageRequestDTO)를 이용하여 페이지 관련 정보를 가져오고
//             Pageable pageable = pageRequestDTO.getPageable(Sort.by("reviewNum").descending());

//             // 서비스 계층을 통해 페스티벌 데이터를 페이지네이션하여 가져옵니다.
//             Page<ReviewDTO> reviewPage = reviewService.findAllByOrderByReviewNum(pageable);
//             reviews = reviewPage.getContent(); // 현재 페이지의 축제 목록을 가져옴

//             // 총 페이지 수 계산
//             int totalPages = reviewPage.getTotalPages();

//             // 현재 페이지 번호 가져오기
//             int currentPage = pageRequestDTO.getPage();

//             // 페이지 번호 목록 생성 (고정된 3개 페이지만 표시)
//             // 현재 페이지를 중심으로 앞뒤 1페이지만 보여주도록 제한
//             List<Integer> pageNumbers = IntStream
//                     .rangeClosed(Math.max(1, currentPage - 1), Math.min(currentPage + 1, totalPages))
//                     .boxed()
//                     .collect(Collectors.toList());

//             // 이전 페이지와 다음 페이지 버튼을 제어하기 위한 조건 설정
//             boolean hasPrevPage = currentPage > 1; // 현재 페이지가 1보다 크면 이전 페이지가 있다는 의미
//             boolean hasNextPage = currentPage < totalPages; // 현재 페이지가 총 페이지 수보다 작으면 다음 페이지가 있다는 의미

//             // 이전 페이지 번호와 다음 페이지 번호 계산
//             int prevPageNumber = currentPage - 1;
//             int nextPageNumber = currentPage + 1;

//             // 모델에 페이지 관련 데이터를 추가하여 뷰로 전달
//             model.addAttribute("pageNumbers", pageNumbers); // 페이지 번호 목록
//             model.addAttribute("hasPrevPage", hasPrevPage); // 이전 페이지 버튼 제어를 위한 조건
//             model.addAttribute("hasNextPage", hasNextPage); // 다음 페이지 버튼 제어를 위한 조건
//             model.addAttribute("prevPageNumber", prevPageNumber); // 이전 페이지 번호
//             model.addAttribute("nextPageNumber", nextPageNumber); // 다음 페이지 번호
//         }

//         model.addAttribute("result", reviews); // 축제 목록을 모델에 추가
//         model.addAttribute("searchQuery", searchQuery); // 검색어를 모델에 추가
//         model.addAttribute("isSearch", isSearch); // 검색 여부를 모델에 추가

//         return "review/reviewList"; // "festivalList.html" 페이지로 이동
//     }

    // @GetMapping("/reviewList")
    // public void getReviews(PageRequestDTO pageRequestDTO, Model model) {
    //     //model.addAttribute("result", reviewRepository.getReviewNum(1L));
    //     //model.addAttribute("result", reviewRepository.getReviewJoinMember(1L));
    //     //model.addAttribute("result", reviewRepository.getReviewList());

    //     model.addAttribute("result", reviewService.getAllReviews());

    // }

    // 검색 기능 및 페이징을 위한 메서드
    @GetMapping("/reviewList")
    public String review(
            @RequestParam(name = "searchQuery", required = false) String searchQuery,
            PageRequestDTO pageRequestDTO, Model model) {

        List<ReviewDTO> reviews; // 축제 목록을 담을 변수
        boolean isSearch = false; // 검색 여부를 나타내는 변수

        if (searchQuery != null && !searchQuery.isEmpty()) {
            // 검색어가 제공된 경우, 해당 검색어를 사용하여 축제를 검색합니다.
            reviews = reviewService.searchReviews(searchQuery); // 검색된 축제 목록을 가져옴
            isSearch = true; // 검색 여부 플래그를 true로 설정
        } else {
            // 검색어가 없는 경우, 페이징을 위한 로직을 수행합니다.

            // 페이지 요청 정보(pageRequestDTO)를 이용하여 페이지 관련 정보를 가져오고
            Pageable pageable = pageRequestDTO.getPageable(Sort.by("reviewNum").descending());

            // 서비스 계층을 통해 페스티벌 데이터를 페이지네이션하여 가져옵니다.
            Page<ReviewDTO> reviewPage = reviewService.findAllByOrderByReviewNum(pageable);
            reviews = reviewPage.getContent(); // 현재 페이지의 축제 목록을 가져옴

            // 총 페이지 수 계산
            int totalPages = reviewPage.getTotalPages();

            // 현재 페이지 번호 가져오기
            int currentPage = pageRequestDTO.getPage();

            // 페이지 번호 목록 생성 (고정된 3개 페이지만 표시)
            // 현재 페이지를 중심으로 앞뒤 1페이지만 보여주도록 제한
            List<Integer> pageNumbers = IntStream
                    .rangeClosed(Math.max(1, currentPage - 1), Math.min(currentPage + 1, totalPages))
                    .boxed()
                    .collect(Collectors.toList());

            // 이전 페이지와 다음 페이지 버튼을 제어하기 위한 조건 설정
            boolean hasPrevPage = currentPage > 1; // 현재 페이지가 1보다 크면 이전 페이지가 있다는 의미
            boolean hasNextPage = currentPage < totalPages; // 현재 페이지가 총 페이지 수보다 작으면 다음 페이지가 있다는 의미

            // 이전 페이지 번호와 다음 페이지 번호 계산
            int prevPageNumber = currentPage - 1;
            int nextPageNumber = currentPage + 1;

            // 모델에 페이지 관련 데이터를 추가하여 뷰로 전달
            model.addAttribute("pageNumbers", pageNumbers); // 페이지 번호 목록
            model.addAttribute("hasPrevPage", hasPrevPage); // 이전 페이지 버튼 제어를 위한 조건
            model.addAttribute("hasNextPage", hasNextPage); // 다음 페이지 버튼 제어를 위한 조건
            model.addAttribute("prevPageNumber", prevPageNumber); // 이전 페이지 번호
            model.addAttribute("nextPageNumber", nextPageNumber); // 다음 페이지 번호
        }

        model.addAttribute("result", reviews); // 축제 목록을 모델에 추가
        model.addAttribute("searchQuery", searchQuery); // 검색어를 모델에 추가
        model.addAttribute("isSearch", isSearch); // 검색 여부를 모델에 추가

        return "review/reviewList"; // "festivalList.html" 페이지로 이동
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
        
        redirect.addAttribute("reviewNum", dto.getReviewNum());
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