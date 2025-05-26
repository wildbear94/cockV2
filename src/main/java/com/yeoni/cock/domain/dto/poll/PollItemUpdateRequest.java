package com.yeoni.cock.domain.dto.poll;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollItemUpdateRequest {
    @NotBlank(message = "투표 항목 설명은 필수 입력값입니다.")
    private String itemDescription;

    @NotNull(message = "투표 항목 순서는 필수 입력값입니다.")
    private Integer itemOrder;
} 