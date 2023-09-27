package com.whiteboard.whiteboard.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;
import com.whiteboard.whiteboard.service.MemberService;
import com.whiteboard.whiteboard.service.MemberServiceImpl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private final MemberRepository memberRepository;

    @GetMapping({ "/", "", "main" }) // 모든 요청에서 메인으로 갈수있게.
    public String main() {
        return "/member/main";
    }

    // @GetMapping("/main")
    // public String main(){

    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    // String userName = auth.getName();

    // return "main";
    // }

    // main.html에서 login.html 로 넘어가는 메서드
    @GetMapping("/member/login")
    public String loginPage() {
        return "/member/login";
    }

    // // login.html 에서 로그인 정보를 받아서 main.html 로 넘어오는 메서드
    // @PostMapping("/member/login")
    // public String loginProcess(@RequestParam("email") String email,
    // @RequestParam("pw") String pw,
    // HttpSession session, Model model, @ModelAttribute("MemberDTO") MemberDTO
    // memberDTO) {
    // Optional<Member> memberOptional = memberService.login(email, pw);

    // // 로그인 됐는지 확인
    // System.err.println("!!!!!! 로그인 확인~~~~~~~~~ =---> " +
    // memberOptional.isPresent());

    // if (memberOptional.isPresent()) {
    // Member member = memberOptional.get();
    // session.setAttribute("loggedInUser", member); // 세션에 사용자 정보 저장

    // // 로그인 유저 정보 확인
    // System.err.println("!!!!!! 유저정보 확인~~~~~~ ----> " +
    // session.getAttribute("loggedInUser"));

    // return "redirect:/main"; // 로그인 후 메인 페이지로 리다이렉트
    // } else {
    // model.addAttribute("loginError", true);
    // return "/member/login"; // 로그인 실패 시 로그인 페이지로 이동
    // }
    // }

    // 로그인 메서드
    // login.html 에서 로그인 정보를 받아서 main.html 로 넘어오는 메서드
    // , @ModelAttribute("MemberDTO") MemberDTO memberDTO
    @PostMapping("/member/login")
    public String login(@RequestParam("email") String email, @RequestParam("pw") String pw, HttpSession session,
            Model model, MemberDTO memberDTO) {

        Optional<Member> optionalMember = memberService.login(email, pw);

        if (optionalMember.isPresent()) { // 회원의 이메일, 비밀번호가 일치할 때

            Member member = optionalMember.get();

            session.setAttribute("loggedInUser", member); // 세션에 사용자 정보 저장

            // getAttribute는 Object타입으로 가져온다
            System.err.println("!!!!!! 유저정보 확인~~~~~~ ----> " + session.getAttribute("loggedInUser"));

            memberDTO = memberService.covertSessionToDTO(session);

            return "redirect:/main";
        } else { // 회원의 이메일, 비밀번호가 일치하지 않을 때
            return "/member/login";
        }

    }

    // 로그아웃 메서드
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {

        session.removeAttribute("loggedInUser");

        return "redirect:/main";
    }

    // @GetMapping("/user")
    // public String dashBoardPage(@AuthenticationPrincipal UserDetails user, Model
    // model){
    // model.addAttribute("loginId",user.getUsername() );
    // model.addAttribute("loginRoles",user.getAuthorities() );
    // return "user";
    // }

    @GetMapping("/member/myPage")
    public String dashBoardPage(HttpSession session, Model model) {
        // myPage에서 세션 정보 읽어오기
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // 로그인된 사용자 정보를 모델에 추가
            model.addAttribute("member", loggedInUser);
            return "/member/myPage";
        } else {
            // 로그인되지 않았을 경우 로그인 페이지로 리다이렉트 또는 처리
            return "redirect:/member/login";
        }
    }

    // @GetMapping("/member/myPage")
    // public String modify(HttpSession session, Model model){

    // Member loggedInUser = (Member) session.getAttribute("loggedInUser");

    // if(loggedInUser != null){

    // return "/member/myPage";
    // }else{
    // //로그인 되지 않았을 경우 로그인 페이지로 redirect 처리
    // return "redirect:/member/login";
    // }

    // }

    // 리포지토리 컨트롤러 서비스 html

    @GetMapping("/member/checkedPassword")
    public String CheckedPassword(HttpSession session, Model model, @ModelAttribute("memberDTO") MemberDTO memberDTO) {
        // GET 요청에 대한 페이지를 반환하거나 다른 작업 수행

        // 세션에서 현재 로그인한 사용자 정보를 가져옵니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        memberDTO = memberService.covertSessionToDTO(session);

        model.addAttribute(loggedInUser);
        
        
        System.out.println("dto에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" + memberDTO.getPw());
        System.out.println("dto에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" + memberDTO);
        System.out.println("loggedInUser에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" +loggedInUser.getPw());
        System.out.println("loggedInUser에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" +loggedInUser);

        return "/member/checkedPassword"; // 적절한 뷰 이름 사용
    }

    // 비밀번호 확인 메서드
    @PostMapping("/member/checkedPassword")
    public String checkedPassword(HttpSession session, Model model, @ModelAttribute("MemberDTO") MemberDTO memberDTO) {

        // 세션에서 현재 로그인한 사용자 정보를 가져옵니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        model.addAttribute(loggedInUser);
        
        memberDTO = memberService.covertSessionToDTO(session);
        Optional<Member> optionalMember = memberService.login(loggedInUser.getEmail(), memberDTO.getPw());

        System.out.println("optional 알아보기 >>>>>>>>>>>>>>>> " +  optionalMember);
        System.out.println("dto에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" + memberDTO.getPw());
        System.out.println("dto에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" + memberDTO);
        System.out.println("loggedInUser에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" +loggedInUser.getPw());
        System.out.println("loggedInUser에 들어있는 비밀번호 보는 콘솔~~~>>>>>>>>>>>>" +loggedInUser);
        if (optionalMember.isPresent()) {
            // 업데이트 후, 사용자를 다시 마이페이지로 리다이렉트합니다.
            return "/member/myPage";
        } else {
            return "redirect:/member/checkedPassword";
        }

    }

    // 회원정보 수정 메서드
    @PostMapping("/member/myPage")
    public String modify(HttpSession session, @ModelAttribute MemberDTO memberDTO) {

        // 세션에서 현재 로그인한 사용자 정보를 가져옵니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // 사용자 정보 업데이트 로직을 수행합니다.
            loggedInUser.updateNickname(memberDTO.getNickname()); // 닉네임 필드를 업데이트합니다.
            // 다른 필드들도 필요에 따라 직접 업데이트할 수 있습니다.

            // 여기에서 데이터베이스 업데이트 또는 다른 작업을 수행할 수 있습니다.
            memberRepository.save(loggedInUser);

            // 업데이트 후, 사용자를 다시 마이페이지로 리다이렉트합니다.
            return "redirect:/member/myPage";
        } else {
            // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트 처리
            return "redirect:/member/login";
        }
    }

    @GetMapping("/member/delete")
    public String delete(HttpSession session, @ModelAttribute MemberDTO memberDTO) {

        // 세션에서 현재 로그인한 사용자 정보를 가져옵니다.
        Member loggedInUser = (Member) session.getAttribute("loggedInUser");

        memberRepository.delete(loggedInUser);
        session.removeAttribute("loggedInUser");

        // 로그인 되지 않은 경우 로그인 페이지로 리다이렉트 처리
        return "redirect:/main";
    }

    /*
     * public String dashBoardPage(@AuthenticationPrincipal UserDetails user, Model
     * model) {
     *
     * // 사용자 이름을 기반으로 데이터베이스에서 정보 가져오기
     * Optional<Member> memberOptional =
     * memberRepository.findByEmail(user.getUsername());
     * if (memberOptional.isPresent()) {
     * Member member = memberOptional.get();
     * model.addAttribute("loginName", member.getName());
     * model.addAttribute("loginId", member.getEmail());
     * model.addAttribute("loginRoles", user.getAuthorities());
     * model.addAttribute("phoneNumber", member.getPhoneNum());
     * model.addAttribute("nickname", member.getNickname());
     * model.addAttribute("birthDay", member.getBirthDay());
     * }
     * return "/member/myPage";
     * }
     */

    // 시큐리티 때 활용할 회원가입..
    // //회원가입 창 가져오기
    // @GetMapping("/registerMember")
    // public ModelAndView showRegistrationForm(){
    // return new ModelAndView("registerMember");
    // }

    // // 회원가입
    // @PostMapping("/registerMember")
    // public ResponseEntity<String> registerMember(@RequestBody Member member) {
    // // 회원 정보를 저장하고 결과를 반환
    // try {
    // memberRepository.save(member);
    // return ResponseEntity.ok("회원 가입이 완료되었습니다.");
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입 중
    // 오류가 발생했습니다.");
    // }
    // }

    @Autowired
    private final MemberService memberService;

    @Autowired
    private final MemberServiceImpl memberServiceImpl;

    // 회원가입창 가져오기
    @GetMapping("/member/registerMember")
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("/member/registerMember");
    }

    @PostMapping("/member/registerMember")
    public ResponseEntity<?> memberRegister(@RequestBody Member member) {
        String email = member.getEmail();
        String phoneNum = member.getPhoneNum();
        String nickname = member.getNickname();
        String name = member.getName();

        // 이메일, 전화번호, 닉네임 중복 확인
        if (memberService.isEmailRegistered(email)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 이메일 주소입니다.");
        }

        if (memberService.isPhoneNumberRegistered(phoneNum)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 전화번호입니다.");
        }

        if (memberService.isNicknameRegistered(nickname)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("중복된 닉네임입니다.");
        }

        // 회원가입 메서드 호출
        memberService.memberRegister(member);

        // // 회원가입이 성공하면 회원가입 축하 메시지와 함께 로그인 페이지로 리디렉션
        // String successMessage = name + "님, 회원가입 축하드립니다.";
        // return ResponseEntity.status(HttpStatus.FOUND)
        // .header(HttpHeaders.LOCATION, "/login")
        // .body(successMessage);

        // 회원가입이 성공하면 JSON 응답 반환
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        return ResponseEntity.ok(response);
    }

}
