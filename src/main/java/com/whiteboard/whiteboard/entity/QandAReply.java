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
//질의응답댓글
public class QandAReply extends BaseEntity {
    
    @Id //PK 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment 자동증가
    private Long replyNum; //댓글번호

    @ManyToOne(fetch = FetchType.LAZY) //FK 외래키
    private QandA questionNum; //질문번호

    private int replyLevel; //댓글수준
    private int replySetp; //댓글순서
    
    @ManyToOne(fetch = FetchType.LAZY)
	private Member writer; //작성자 : Member 엔티티의 id 컬럼
    private String content; //내용

}
