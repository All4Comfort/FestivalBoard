package com.whiteboard.whiteboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.entity.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
//1번째 시도
    @Query(value = "SELECT * FROM festival ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<FestivalDTO> findRandomFive();

//2번째 시도
    @Query("SELECT new com.whiteboard.whiteboard.dto.FestivalDTO(f.festivalTitle, f.thumnail) FROM Festival f")
    List<FestivalDTO> findAllAsDTO();

    // @Modifying
    // @Query("update Festival e set e.read_count = e.read_count + 1 where
    // e.festival_num = :festival_num")
    // int updateReadCount(Long festivalNum);

}
