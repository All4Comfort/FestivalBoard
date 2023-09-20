package com.whiteboard.whiteboard.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.NoticeDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.PageResultDTO;
import com.whiteboard.whiteboard.entity.Member;
import com.whiteboard.whiteboard.entity.Notice;
import com.whiteboard.whiteboard.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{

  private final NoticeRepository noticeRepository;

  // 게시글 등록 메서드
  @Override
  public Long register(NoticeDTO dto) {
    // DTO를 엔티티로 변환하고 저장 후, 저장된 게시글의 번호를 반환
    Notice notice = dtoToEntity(dto);
    noticeRepository.save(notice);
    return notice.getNoticeNum();
  }

// 페이지 요청 정보를 기반으로 게시글 목록을 가져오는 메서드
@Override
public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
  
  // 엔터티를 DTO로 변환하는 함수를 정의
  Function<Object[], NoticeDTO> fn = (en -> entityToDTO((Notice)en[0], (Member)en[1]));

  // Pageable을 이용하여 정렬 조건을 적용하고, 게시글 목록을 가져옴
  Page<Object[]> result = noticeRepository.getNoticeWithWriter(pageRequestDTO.getPageable(Sort.by("noticeNum").descending()));

  return new PageResultDTO<>(result, fn);
}
    
}
