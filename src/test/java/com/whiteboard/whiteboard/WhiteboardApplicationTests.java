package com.whiteboard.whiteboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

@SpringBootTest
class WhiteboardApplicationTests {

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

	@Test

	public void memberRE() {
		// 조회하려는 회원의 id (기본 키)를 알고 있다고 가정
		String memberId = "chanol91@naver.com";

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
		} else {
			System.out.println("해당 ID에 해당하는 회원 정보가 없습니다.");
		}
	}
}
