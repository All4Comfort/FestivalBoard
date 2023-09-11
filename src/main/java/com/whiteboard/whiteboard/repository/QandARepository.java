package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.QandA;

public interface QandARepository extends JpaRepository<QandA, String> {

}
