package com.whiteboard.whiteboard.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
//공통 컬럼을 정의한 엔티티로, 자체 테이블 생성하지 않고 다른 테이블에 컬럼을 상속함. 
public class BaseEntity {
	
	@CreatedDate //해당 필드 즉 컬럼에 날짜값이 자동 반영되도록 선언
	@Column(name = "registerDate", updatable = false)
	//DB에 registerDate 컬럼 생성하도록 선언 및 값이 처음 insert 이후엔 자동 update 불가하도록 설정
	private LocalDateTime registerDate; //작성일
	
	@CreatedDate 
	@Column(name = "modifyDate")
	private LocalDateTime modifyDate; //수정일
}
