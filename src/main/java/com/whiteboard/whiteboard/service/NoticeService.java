package com.whiteboard.whiteboard.service;

import com.whiteboard.whiteboard.dto.NoticeDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.PageResultDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.entity.Notice;

public interface NoticeService {
    // 신규글 등록 메서드
    Long register(NoticeDTO dto);

    // list 페이지에서 페이지에 해당하는 글목록 조회 리스트 get 메서드 정의
    PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    // 설명: Entity 객체를 DTO로 변환하는 메서드
    default NoticeDTO entityToDTO(Notice notice, Member member){
        NoticeDTO dto = NoticeDTO.builder()
                        .noticeNum(notice.getNoticeNum())
                        .writer(member.getName())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .registerDate(notice.getRegisterDate())
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
}
