package com.whiteboard.whiteboard.entity;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//회원정보
public class Member extends BaseEntity implements UserDetails{

	@Id
	private String email; //아이디 : 이메일주소로 받기
	
	private String pw; //비밀번호 : 암호화해줘야 함

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
	public void updatePw(String newPW) {

		this.pw = newPW;
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

//회원이 DB에 인서트(생성)될 때 권한 부여하기

//권한 엔티티를 제네릭으로 준 컬랙션 생성
//@Builder.Default
//아래 어노테이션은 자동으로 참조를 걸어줌
//@ElementCollection(fetch = FetchType.LAZY) //권한값을 항상 가져올 텐데 LAZY가 꼭 필요한가..? 추후 수정할 수도...
//private Set<AuthoritySet> authority = new HashSet<>(); 

//회원이 DB에 인서트(생성) 시 권한 부여 메서드
//public void addAuthority(AuthoritySet authoritySet){
	//authority.add(authoritySet);
//}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
}

@Override
public String getPassword() {
	return pw;
}

@Override
public String getUsername() {
	return email;
}

@Override
public boolean isAccountNonExpired() {
	return true;
}

@Override
public boolean isAccountNonLocked() {
	return true;
}

@Override
public boolean isCredentialsNonExpired() {
	return true;
}

@Override
public boolean isEnabled() {
	return true;
}

}