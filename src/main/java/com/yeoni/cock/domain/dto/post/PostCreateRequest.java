package com.yeoni.cock.domain.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    @NotNull(message = "게시판 ID는 필수 입력값입니다.")
    private Long boardId;

    @NotBlank(message = "제목은 필수 입력값입니다.")
    @Size(max = 200, message = "제목은 200자를 초과할 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    private String content;

    private boolean isSecret;
    private boolean isNotice;
} 