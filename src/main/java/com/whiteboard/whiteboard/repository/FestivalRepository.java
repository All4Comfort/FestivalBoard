package com.whiteboard.whiteboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.whiteboard.whiteboard.entity.Festival;

public interface FestivalRepository extends JpaRepository<Festival, Long> {

    @Modifying
    @Query("update Festival e set e.read_count = e.read_count + 1 where e.festival_num = :festival_num")
    int updateReadCount(Long festivalNum);
}
