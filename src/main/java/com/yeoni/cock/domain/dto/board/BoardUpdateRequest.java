package com.yeoni.cock.domain.dto.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateRequest {
    @NotBlank(message = "게시판 이름은 필수 입력값입니다.")
    @Size(max = 100, message = "게시판 이름은 100자를 초과할 수 없습니다.")
    private String name;

    private String description;

    @NotBlank(message = "게시판 타입은 필수 입력값입니다.")
    private String type;
} 