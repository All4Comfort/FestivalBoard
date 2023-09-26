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
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
//축제정보댓글
public class FestivalReply extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replyNum; //댓글번호
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(nullable = false)
	private Festival from; //축제글번호
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(nullable = false)
	private Member writer; //작성자 : Member 엔티티의 email 컬럼

	@Column(nullable = false)
	private String content; //댓글내용

	private	int replyLevel; //댓글수준 : 댓글(1)/대댓글(2)까지만
	
	private int replyStep; //댓글순서 : 댓글1/댓글1-1/댓글1-2/댓글2/댓글2-1
	
	// 내용 업데이트 메서드
	public void updateFContent(String content){
		this.content = content;
	}
}
