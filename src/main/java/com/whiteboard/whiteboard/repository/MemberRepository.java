package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whiteboard.whiteboard.entity.Member;


public interface MemberRepository extends JpaRepository<Member,String>{
  
  //@EntityGraph : 내부적으로 lazy 조인이 걸린 엔티티 간의 left join을 자동으로 수행함
	//별도의 조인 쿼리를 JPQL에 설정하지 않더라도, 연관 테이블의 FK(Foreign Key)를 찾아서 LEFT JOIN함
	//속성path와 ...Graph 로더를 넘기는 속성이 있는데 일반적으로 아래처럼 사용합니다.
	

	// @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
	// @Query("SELECT m FROM member m WHERE m.isSocial = :isSocial AND m.id = :id")
	// Optional<Member> findByEmail(@Param("id") String id, @Param("isSocial") boolean isSocial);

}
