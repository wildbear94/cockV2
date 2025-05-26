package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private Long commentId;
    private Long postId;
    private Long parentId;
    private Long clubId;
    private String content;
    private String authorId;
    private int likeCount;
    private boolean isSecret;
    private String status;
    private boolean isDeleted;
    private LocalDateTime createdAt;
} 