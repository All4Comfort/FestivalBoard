package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.MemberDTO;
import com.whiteboard.whiteboard.dto.QuestionDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.entity.Question;
import com.whiteboard.whiteboard.repository.MemberRepository;
import com.whiteboard.whiteboard.repository.QuestionRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

  private final QuestionRepository questionRepository;

  private List<QuestionDTO> searchResults = new ArrayList<>();

  private final MemberService memberService;

  private final MemberRepository memberRepository;

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

  @Override
  public void modify(QuestionDTO dto) {
    System.out.println("모디파이 메서드 ~~!!!!!!!!!!!!!!!!!!!!!!!!!!!" + dto);
    Question question = questionRepository.getReferenceById(dto.getQuestionNum());
    question.updateContent(dto.getContent());
    question.updateTitle(dto.getTitle());
    questionRepository.save(question);
  }
  
  public Question dtoToEntity(QuestionDTO questionDTO, HttpSession session) {
  
  MemberDTO memberDTO = memberService.covertSessionToDTO(session);
  
  Member member = memberRepository.getReferenceById(memberDTO.getEmail());
  
  Question question = Question.builder()
  //리뷰 엔티티의 writer는 Member타입임!!!!!!!!!!!!!!!
  .writer(member)
        .build();
    return question;
  }
}
