package com.whiteboard.whiteboard.service;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.NoticeDTO;
import com.whiteboard.whiteboard.dto.PageRequestDTO;
import com.whiteboard.whiteboard.dto.PageResultDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService{@Override
  public Long register(NoticeDTO dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'register'");
  }

@Override
public PageResultDTO<NoticeDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'getList'");
}
    
}
