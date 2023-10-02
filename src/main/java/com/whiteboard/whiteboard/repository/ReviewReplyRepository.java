package com.whiteboard.whiteboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.whiteboard.whiteboard.entity.ReviewReply;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long>{
  @Query("SELECT r FROM ReviewReply r WHERE r.from = : reviewNum ORDER BY r.from DESC")
   List<ReviewReply> findAllOrderByReviewNumDesc(@Param("reviewNum") Long reviewNum);
}
