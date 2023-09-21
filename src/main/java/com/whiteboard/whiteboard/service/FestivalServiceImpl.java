package com.whiteboard.whiteboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.dto.forAPI.BusanDTO;

@Service
//@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService {

     // 축제 정보를 담을 리스트
    private final List<FestivalDTO> festivals = new ArrayList<>();

    public void FestivalService() {
        // 여기서 festivals 리스트를 초기화할 수 있습니다.
        // 예를 들어, 데이터베이스에서 가져온 축제 정보를 초기화할 수 있습니다.
    }

	@Override
	public List<BusanDTO> getRandomFestivals() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getRandomFestivals'");
	}
    
    
}
