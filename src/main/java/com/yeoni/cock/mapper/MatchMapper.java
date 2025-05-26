package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Match;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MatchMapper {
    List<Match> selectAll();
    Match selectById(@Param("matchKey") Integer matchKey);

    int insert(Match match);
    int update(Match match);

    /** 소프트 삭제 */
    int softDelete(@Param("matchKey") Integer matchKey);

}
