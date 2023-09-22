package com.whiteboard.whiteboard.repository;

import java.util.List;

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

// 특정 게시글에 댓글이 몇개 존재하는지 여부 쿼리 작성
@Query("Select n From Notice n Where n.noticeNum = :noticeNum")
Object[] getNoticeBynoticeNum(@Param("noticeNum") Long noticeNum);

// 특정 게시글에 댓글이 몇개 존재하는지 여부 쿼리 작성
@Query("Select n From Notice n")
List<Notice> getNoticeList();
}
