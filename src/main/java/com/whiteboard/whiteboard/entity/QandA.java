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
//질의응답게시판
public class QandA extends BaseEntity{ //작성일,수정일은 BaseEntity로 자동 기입
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionNum;//질문번호

    @ManyToOne(fetch = FetchType.LAZY)
	private Member writer; //작성자 : Member 엔티티의 id 컬럼

    @Column(nullable = false)
    private String title;//제목

    @Column(nullable = false)
    private String content;//글내용
    
    private Boolean isSecret;//비밀글 여부
    private String state;//진행상태 : 답변미완료, 답변완료

}
