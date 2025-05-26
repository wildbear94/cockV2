package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.PollItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PollItemMapper {
    List<PollItem> findByPollId(@Param("pollId") Long pollId);
    Optional<PollItem> findById(@Param("itemId") Long itemId);
    void save(@Param("item") PollItem item);
    void update(@Param("item") PollItem item);
    void delete(@Param("itemId") Long itemId);
} 