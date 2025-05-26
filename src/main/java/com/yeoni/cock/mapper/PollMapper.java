package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Poll;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PollMapper {
    List<Poll> findByClubId(@Param("clubId") Long clubId);
    Optional<Poll> findById(@Param("pollId") Long pollId);
    void save(@Param("poll") Poll poll);
    void update(@Param("poll") Poll poll);
    void delete(@Param("pollId") Long pollId);
} 