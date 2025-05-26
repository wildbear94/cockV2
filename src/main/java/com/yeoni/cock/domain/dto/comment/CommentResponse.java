package com.yeoni.cock.domain.dto.comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CommentResponse {
    private Long commentId;
    private Long postId;
    private Long parentId;
    private Long clubId;
    private String content;
    private String authorId;
    private int likeCount;
    private boolean isSecret;
    private String status;
    private LocalDateTime createdAt;
    private List<CommentResponse> replies;
} 