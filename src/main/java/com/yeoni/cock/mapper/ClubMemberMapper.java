package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.ClubMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ClubMemberMapper {
    List<ClubMember> findByUserId(@Param("userId") String userId);
    List<ClubMember> findByClubId(@Param("clubId") Long clubId);
    Optional<ClubMember> findByClubIdAndUserId(@Param("clubId") Long clubId, @Param("userId") String userId);
    int countByUserId(@Param("userId") String userId);
    void save(@Param("member") ClubMember member);
    void update(@Param("member") ClubMember member);
    void delete(@Param("memberId") Long memberId);
} 