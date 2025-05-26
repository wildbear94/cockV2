package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    List<Board> findByClubId(@Param("clubId") Long clubId);
    Optional<Board> findById(@Param("boardId") Long boardId);
    void save(@Param("board") Board board);
    void update(@Param("board") Board board);
    void delete(@Param("boardId") Long boardId);
} 