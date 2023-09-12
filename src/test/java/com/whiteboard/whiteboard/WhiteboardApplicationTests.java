package com.whiteboard.whiteboard;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

@SpringBootTest
class WhiteboardApplicationTests {

	@Autowired
	private MemberRepository memberRepository;//멤버 레포지토리

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	public void memberregister() {//DB에 회원가입 member 테이블에 넣기

		//password 테스트
		String hashedPassword = passwordEncoder.encode("77777");

		Member member = Member.builder()
						.id("chanol91@naver.com")
						.password(hashedPassword)
						.phoneNum("01094800129")
						.nickname("민건왕자")
						.gender("하남자")
						.birthDay(LocalDate.parse("1991-05-02"))
						.name("김민건")
						.isSns(false)
						.build();
		memberRepository.save(member);

		System.err.println(member);
	}

	

}
