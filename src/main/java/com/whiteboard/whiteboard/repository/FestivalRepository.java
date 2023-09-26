package com.whiteboard.whiteboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.whiteboard.whiteboard.entity.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {

    //한울쓰
    @Query(value = "SELECT f FROM Festival f ORDER BY RAND() LIMIT 5")
    //@Query(value = "SELECT f FROM Festival f WHERE f.festivalNum <= 5")
    List<Festival> getFiveEntity();

    //@Query("SELECT f FROM Festival f ORDER BY f.festivalNum = :festivalNum")
    //Optional<Festival> findByFestivalNum(@Param("festivalNum") Long festivalNum);


    //민건 리스트로 가져오는 축제게시물~~~~~
    @Query("SELECT f FROM Festival f ORDER BY f.festivalNum ASC")
    List<Festival> findAllByOrderByFestivalNumAsc();

    //축제 리스트 페이징
    @Query("SELECT f FROM Festival f ORDER BY f.festivalNum ASC")
    Page<Festival> findAllByOrderByFestivalNum(Pageable pageable);

    //축제 검색
    List<Festival> findByFestivalTitleContaining(String searchQuery);

    // //축제 정보 상세페이지
    // @Query("SELECT f FROM Festival f ORDER BY f.festivalNum = :festivalNum")

    

    // @Modifying
    // @Query("update Festival e set e.read_count = e.read_count + 1 where
    // e.festival_num = :festival_num")
    // int updateReadCount(Long festivalNum);

}
