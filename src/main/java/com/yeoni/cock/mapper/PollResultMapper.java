package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.PollResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PollResultMapper {
    List<PollResult> findByPollId(@Param("pollId") Long pollId);
    List<PollResult> findByUserId(@Param("pollId") Long pollId, @Param("userId") String userId);
    Optional<PollResult> findById(@Param("resultId") Long resultId);
    void save(@Param("result") PollResult result);
    void delete(@Param("resultId") Long resultId);
    void deleteByPollIdAndUserId(@Param("pollId") Long pollId, @Param("userId") String userId);
    int countByPollIdAndItemId(@Param("pollId") Long pollId, @Param("itemId") Long itemId);
} 