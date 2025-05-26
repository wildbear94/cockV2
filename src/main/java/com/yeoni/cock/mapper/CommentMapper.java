package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {
    List<Comment> findByPostId(@Param("postId") Long postId);
    Optional<Comment> findById(@Param("commentId") Long commentId);
    void save(@Param("comment") Comment comment);
    void update(@Param("comment") Comment comment);
    void delete(@Param("commentId") Long commentId);
} 