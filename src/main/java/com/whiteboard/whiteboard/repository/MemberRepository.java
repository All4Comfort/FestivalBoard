package com.whiteboard.whiteboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Member;


public interface MemberRepository extends JpaRepository<Member,String>{

    List<Member> findAllById(String targetId);

    
    
}
