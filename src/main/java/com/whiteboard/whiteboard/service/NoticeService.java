package com.whiteboard.whiteboard.service;

import java.util.List;

import com.whiteboard.whiteboard.dto.NoticeDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.PageResultDTO;
import com.whiteboard.whiteboard.dto.QuestionDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.entity.Notice;
import com.whiteboard.whiteboard.entity.Question;

public interface NoticeService {
    // 신규글 등록 메서드
    Long register(NoticeDTO dto);

    // 신규글 등록 메서드
    Long registerQuestion(QuestionDTO dto);

    //모든 공지글 가져오기
    List<NoticeDTO> findAll();

    //모든 공지글 가져오기
    List<QuestionDTO> findAllQuestion();

    // 특정 게시물의 정보를 리턴라는 메서드 선언
    NoticeDTO get(Long noticeNum);

    // 특정 게시물의 정보를 리턴라는 메서드 선언
    NoticeDTO getQuestion(Long questionNum);

    // list 페이지에서 페이지에 해당하는 글목록 조회 리스트 get 메서드 정의
    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 설명: Entity 객체를 DTO로 변환하는 메서드
    default NoticeDTO entityToDTO(Notice notice){
        NoticeDTO dto = NoticeDTO.builder()
                        .noticeNum(notice.getNoticeNum())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .registerDate(notice.getRegisterDate())
                        //.writer(member.getNickname())
                        .build();

        return dto;
    }

    // dtoToEntity 변환 메서드 정의
    default Notice dtoToEntity(NoticeDTO dto){
        Member member = Member.builder().name(dto.getWriter()).build();
        
        Notice notice = Notice.builder()
                        .noticeNum(dto.getNoticeNum())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .build();

        return notice;
    }


    // 설명: Entity 객체를 DTO로 변환하는 메서드
    default QuestionDTO entityToQuestionDTO(Question question){
        QuestionDTO dto = QuestionDTO.builder()
                        .questionNum(question.getQuestionNum())
                        .title(question.getTitle())
                        .content(question.getContent())
                        .registerDate(question.getRegisterDate())
                        .nickName(question.getWriter().getNickname())
                        .build();

        return dto;
    }

    // dtoToEntity 변환 메서드 정의
    default Question dtoToQuestionEntity(QuestionDTO dto){
        Member member1 = Member.builder().nickname(dto.getNickName()).build();

        Question question = Question.builder()
                        .questionNum(dto.getQuestionNum())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .writer(member1)
                        .build();

        return question;
    }

    // 게시물 삭제 메서드 선언.
    void remove(Long questionNum);

    //게시물 수정 메서드 선언
    void modify(NoticeDTO dto);
}
