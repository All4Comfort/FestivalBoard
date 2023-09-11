package com.whiteboard.whiteboard.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//회원정보
public class Member {

	@Id
	private String id; //아이디 : 이메일주소로 받기
	
	private String password; //비밀번호 : 암호화해줘야 함
	private String name; //실명
	private String phoneNum; //전화번호
	private String nickname; //별명
	private String gender; //성별
	private LocalDate birthDay; //생년월일
	private boolean isSns; //소셜계정 인증을 통한 가입 여부
	
}
