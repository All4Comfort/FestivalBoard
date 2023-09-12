package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Member;


public interface MemberRepository extends JpaRepository<Member,String>{
    
}
