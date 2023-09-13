package com.whiteboard.whiteboard.entity;

import jakarta.persistence.Column;
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
//공지게시판
public class Notice extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNum;//공지번호

    @ManyToOne(fetch = FetchType.LAZY)
	private Member writer; //DB에만 저장될 작성자(어느 관리자계정인지) : Member 엔티티의 id 컬럼
    
    @Column(nullable = false)
    private String title;//제목

    @Column(nullable = false)
    private String content;//글내용
   
}

