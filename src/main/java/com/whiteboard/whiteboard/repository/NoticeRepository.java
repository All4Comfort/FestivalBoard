package com.whiteboard.whiteboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whiteboard.whiteboard.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

@Query("SELECT n, m FROM Notice n LEFT JOIN n.writer m WHERE n.noticeNum = :noticeNum")
Object getNoticeWithWriter(@Param("noticeNum") Long noticeNum);

@Query("SELECT n, m FROM Notice n JOIN n.writer m")
Page<Object[]> getNoticeWithWriter(Pageable pageable);


}
