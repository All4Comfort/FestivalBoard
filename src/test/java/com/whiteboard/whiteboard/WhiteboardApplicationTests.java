package com.whiteboard.whiteboard;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.repository.MemberRepository;

@SpringBootTest
class WhiteboardApplicationTests {

	@Autowired
	private MemberRepository memberRepository;//멤버 레포지토리

	@Test
	public void memberregister() {//DB에 회원가입 member 테이블에 넣기

		Member member = Member.builder()
						.id("mingun91@naver.com")
						.password("7777")
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
