package com.whiteboard.whiteboard.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
public class Member extends BaseEntity{

	@Id
	private String id; //아이디 : 이메일주소로 받기
	
	private String password; //비밀번호 : 암호화해줘야 함

	@Column(nullable = false, length = 10)
	private String name; //실명

	@Column(nullable = false,unique = true)
	private String phoneNum; //전화번호

	@Column(nullable = false, unique = true, length = 20)
	private String nickname; //별명

	@Column(nullable = false)
	private String gender; //성별

	@Column(nullable = false)
	private LocalDate birthDay; //생년월일

	private boolean isSns; //소셜계정 인증을 통한 가입 여부
	
	// 여기에 비밀번호 업데이트 로직을 추가할 수 있음
	public void updatePassword(String newPassword) {

		this.password = newPassword;
}

// 실명 업데이트 메서드
public void updateName(String newName) {
		// 여기에 실명 업데이트 로직을 추가할 수 있음
		this.name = newName;
}

// 별명 업데이트 메서드
public void updateNickname(String newNickname) {
		// 여기에 별명 업데이트 로직을 추가할 수 있음
		this.nickname = newNickname;
}

// 전화번호 업데이트 메서드
public void updatePhoneNum(String newPhoneNum) {
		// 여기에 전화번호 업데이트 로직을 추가할 수 있음
		this.phoneNum = newPhoneNum;
}
}
