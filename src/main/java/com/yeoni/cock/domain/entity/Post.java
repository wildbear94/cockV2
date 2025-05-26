package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Post {
    private Long postId;
    private Long boardId;
    private String title;
    private String content;
    private String authorId;
    private int viewCount;
    private int likeCount;
    private boolean isSecret;
    private boolean isNotice;
    private String status;
    private boolean isDeleted;
    private String createdBy;
    private LocalDateTime createdAt;
    private Long updatedBy;
    private LocalDateTime updatedAt;
} 