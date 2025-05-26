package com.yeoni.cock.domain.dto.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequest {
    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private boolean isSecret;
} 