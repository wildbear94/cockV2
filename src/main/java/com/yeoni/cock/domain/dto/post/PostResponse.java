package com.yeoni.cock.domain.dto.post;

import com.yeoni.cock.domain.dto.comment.CommentResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostResponse {
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
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private List<CommentResponse> comments;
} 