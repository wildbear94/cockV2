package com.yeoni.cock.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequest {
    @NotNull(message = "게시글 ID는 필수 입력값입니다.")
    private Long postId;

    private Long parentId;

    @NotNull(message = "클럽 ID는 필수 입력값입니다.")
    private Long clubId;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private boolean isSecret;
} 