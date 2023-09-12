package com.whiteboard.whiteboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;
import com.whiteboard.whiteboard.service.WhiteboardService;

@SpringBootTest
class WhiteboardApplicationTests {

	@Autowired
	private WhiteboardService whiteboardService;

	@Autowired
	private MemberRepository memberRepository;// 멤버 레포지토리

	@Autowired
	private PasswordEncoder passwordEncoder;// 비밀번호 인코더

	// @Test
	// public void memberregister() {//DB에 회원가입 member 테이블에 넣기

	// //password 테스트
	// String hashedPassword = passwordEncoder.encode("77777");

	// Member member = Member.builder()
	// .id("chanol91@naver.com")
	// .password(hashedPassword)
	// .phoneNum("01094800129")
	// .nickname("민건왕자")
	// .gender("하남자")
	// .birthDay(LocalDate.parse("1991-05-02"))
	// .name("김민건")
	// .isSns(false)
	// .build();
	// memberRepository.save(member);

	// System.err.println(member);
	// }

	// 회원조회 테스트
	@Test
	public void memberRE() {
		// 조회하려는 회원의 id (기본 키)를 알고 있다고 가정
		String memberId = "chansol91@naver.com";

		// 해당 ID로 회원 정보 조회
		Member member = memberRepository.findById(memberId).orElse(null);

		// 조회된 회원 정보가 null이 아니라면 정보를 출력
		if (member != null) {
			System.out.println("회원 정보: ");
			System.out.println("아이디: " + member.getId());
			System.out.println("전화번호: " + member.getPhoneNum());
			System.out.println("닉네임: " + member.getNickname());
			System.out.println("성별: " + member.getGender());
			System.out.println("생년월일: " + member.getBirthDay());
			System.out.println("이름: " + member.getName());
			System.out.println("sns로그인: " + member.isSns());
			System.out.println("비밀번호: " + member.getPassword());

		} else {
			System.out.println("해당 ID에 해당하는 회원 정보가 없습니다.");
		}
	}

	// 회원 삭제 테스트
	//@Test
	public void deleteMember() {
		String memberId = "chasol91@naver.com";
		Member member = memberRepository.findById(memberId).orElse(null);
		
		System.out.println("DB에 담겨있는 아이디: "+member);

		if (member == null) {
			System.err.println("DB에 해당 아이디가 없습니다.");
			return;
		}

		// 입력한 패스워드
		String inputPassword = "77777"; // 입력한 패스워드를 직접 입력

		// 저장된 회원의 패스워드와 입력한 패스워드를 비교
		boolean isPasswordCorrect = passwordEncoder.matches(inputPassword, member.getPassword());

		if (isPasswordCorrect) {
			// 비밀번호가 일치하면 회원 삭제
			whiteboardService.deleteMember(memberId);
			System.err.println("비밀번호가 일치해서 삭제됨");
		} else {
			// 비밀번호가 일치하지 않을 때
			System.err.println("비밀번호가 틀렸습니다. 확인해주세요.");
		}
	}
}
