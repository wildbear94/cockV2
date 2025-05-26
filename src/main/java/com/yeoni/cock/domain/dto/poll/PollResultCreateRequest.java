package com.yeoni.cock.domain.dto.poll;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollResultCreateRequest {
    @NotNull(message = "투표 ID는 필수 입력값입니다.")
    private Long pollId;

    @NotNull(message = "투표 항목 ID는 필수 입력값입니다.")
    private Long itemId;
} 