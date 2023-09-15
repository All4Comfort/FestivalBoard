package com.whiteboard.whiteboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Member;


public interface MemberRepository extends JpaRepository<Member,String>{
  
	Optional<Member> findByEmail(String email); //이메일(PK)을 이용해서 사용자 정보 가져오기.
  
}
