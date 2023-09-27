// package com.whiteboard.whiteboard.controller;

// import java.util.List;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;

// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.whiteboard.whiteboard.dto.PageRequestDTO;
// import com.whiteboard.whiteboard.dto.QuestionDTO;
// import com.whiteboard.whiteboard.service.QuestionService;

// import jakarta.servlet.http.HttpSession;
// import lombok.RequiredArgsConstructor;

// @Controller
// @RequiredArgsConstructor
// @RequestMapping("/notice")
// public class QuestionController {
//   private final QuestionService questionService;



// // 검색 기능 및 페이징을 위한 메서드
//     @GetMapping("/question")
//     public String question(
//             @RequestParam(name = "searchQuery", required = false) String searchQuery,
//             PageRequestDTO pageRequestDTO, Model model) {

//         List<QuestionDTO> questions; // 축제 목록을 담을 변수
//         boolean isSearch = false; // 검색 여부를 나타내는 변수

//         if (searchQuery != null && !searchQuery.isEmpty()) {
//             // 검색어가 제공된 경우, 해당 검색어를 사용하여 축제를 검색합니다.
//             questions = questionService.searchQuestions(searchQuery); // 검색된 축제 목록을 가져옴
//             isSearch = true; // 검색 여부 플래그를 true로 설정
//         } else {
//             // 검색어가 없는 경우, 페이징을 위한 로직을 수행합니다.

//             // 페이지 요청 정보(pageRequestDTO)를 이용하여 페이지 관련 정보를 가져오고
//             Pageable pageable = pageRequestDTO.getPageable(Sort.by("questionNum").descending());

//             // 서비스 계층을 통해 페스티벌 데이터를 페이지네이션하여 가져옵니다.
//             Page<QuestionDTO> questionPage = questionService.findAllByOrderByQuestionNum(pageable);
//             questions = questionPage.getContent(); // 현재 페이지의 축제 목록을 가져옴

//             // 총 페이지 수 계산
//             int totalPages = questionPage.getTotalPages();

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

//         model.addAttribute("result", questions); // 축제 목록을 모델에 추가
//         model.addAttribute("searchQuery", searchQuery); // 검색어를 모델에 추가
//         model.addAttribute("isSearch", isSearch); // 검색 여부를 모델에 추가

//         return "notice/question"; // "festivalList.html" 페이지로 이동
//     }

//     @GetMapping("/questionDetail")
//     public void questionDetail(@RequestParam("questionNum") Long questionNum, Model model, HttpSession session){
//       QuestionDTO questionDTO = questionService.get(questionNum);
//       model.addAttribute("result", questionDTO);
//     }

//     //신규글등록폼 요청처리
//     @GetMapping("/questionWrite")
//     public void questionWrite(){

//     }

//     //신규글 등록처리
//     @PostMapping("/questionWrite")
//     public String register(@ModelAttribute QuestionDTO dto , RedirectAttributes attributes){
//       Long newQuestionNum = questionService.register(dto);
//       attributes.addFlashAttribute("newQuestionNum", newQuestionNum);
//       return "redirect:/notice/question";
//     }

//     @PostMapping("/remove")
//     public String remove(long questionNum, RedirectAttributes redirect){
//       questionService.remove(questionNum);
//       redirect.addAttribute("newQuestionNum", questionNum);
//       return "redirect:/notice/question";
//     }

// }
