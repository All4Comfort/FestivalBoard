package com.whiteboard.whiteboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.whiteboard.whiteboard.dto.FestivalDTO;
import com.whiteboard.whiteboard.entity.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    //한울쓰
    @Query(value = "SELECT f FROM Festival f ORDER BY RAND() LIMIT 5")
    //@Query(value = "SELECT f FROM Festival f WHERE f.festivalNum <= 5")
    List<Festival> getFiveEntity();


    Optional<Festival> findByFestivalNum(@Param("festivalNum") Long festivalNum);


    //민건 리스트로 가져오는 축제게시물~~~~~
    @Query("SELECT f FROM Festival f ORDER BY f.festivalNum ASC")
    List<FestivalDTO> findAllByOrderByFestivalNumAsc();

    //

    // @Modifying
    // @Query("update Festival e set e.read_count = e.read_count + 1 where
    // e.festival_num = :festival_num")
    // int updateReadCount(Long festivalNum);

}
