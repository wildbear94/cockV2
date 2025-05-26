package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ClubMapper {
    List<Club> findAll(@Param("offset") int offset, @Param("limit") int limit);
    long count();
    Optional<Club> findById(@Param("clubId") Long clubId);
    void save(@Param("club") Club club);
    void update(@Param("club") Club club);
    void delete(@Param("clubId") Long clubId);
} 