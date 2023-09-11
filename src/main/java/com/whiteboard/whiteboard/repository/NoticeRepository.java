package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
   
}
