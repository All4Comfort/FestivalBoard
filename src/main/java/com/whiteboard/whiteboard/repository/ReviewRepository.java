package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
