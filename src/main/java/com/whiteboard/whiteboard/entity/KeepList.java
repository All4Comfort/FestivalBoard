package com.whiteboard.whiteboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//축제정보글 담기 목록
public class KeepList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long keepNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member id; // 회원아이디
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Festival festivalNum; // 축제번호
	
}
