package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.QuestionDTO;
import com.whiteboard.whiteboard.entity.Question;
import com.whiteboard.whiteboard.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;

  private List<QuestionDTO> searchResults = new ArrayList<>();

  @Override
  public Long register(QuestionDTO dto) {
    Question question = dtoToEntity(dto);
    questionRepository.save(question);
    return question.getQuestionNum();
  }

  @Override
  public List<QuestionDTO> searchQuestions(String searchQuery) {
    List<Question> questions = questionRepository.findByTitleContaining(searchQuery);
    List<QuestionDTO> questionDTOs = questions.stream().map(this::entityToDTO).collect(Collectors.toList());
    searchResults = questionDTOs;
    return questionDTOs;
  }

  @Override
  public Page<QuestionDTO> findAllByOrderByQuestionNum(Pageable pageable) {
    Page<Question> questionPage = questionRepository.findAllByOrderByQuestionNum(pageable);
    return questionPage.map(question -> entityToDTO(question));
  }

  @Override
  public QuestionDTO get(Long questionNum) {
    Question question = questionRepository.getQuestionByquestionNum(questionNum);
    return entityToDTO(question);
  }

  @Override
  public void remove(long questionNum) {
    questionRepository.deleteById(questionNum);
  }
  
}
