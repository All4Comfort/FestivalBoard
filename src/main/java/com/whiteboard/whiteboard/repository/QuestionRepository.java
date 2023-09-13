package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, String> {

}
