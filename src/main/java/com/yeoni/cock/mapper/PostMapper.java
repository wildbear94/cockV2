package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    List<Post> findByBoardId(
            @Param("boardId") Long boardId,
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("keyword") String keyword);
    Optional<Post> findById(@Param("postId") Long postId);
    void save(@Param("post") Post post);
    void update(@Param("post") Post post);
    void delete(@Param("postId") Long postId);
    void incrementViewCount(@Param("postId") Long postId);
} 