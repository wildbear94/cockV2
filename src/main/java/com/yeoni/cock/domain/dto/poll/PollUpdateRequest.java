package com.yeoni.cock.domain.dto.poll;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PollUpdateRequest {
    @NotBlank(message = "투표명은 필수 입력값입니다.")
    private String pollName;

    @NotNull(message = "투표 시작일은 필수 입력값입니다.")
    private LocalDate pollStartDate;

    @NotNull(message = "투표 종료일은 필수 입력값입니다.")
    private LocalDate pollEndDate;

    @NotBlank(message = "투표 유형은 필수 입력값입니다.")
    private String pollType;  // MULTIPLE, RANKING, SINGLE

    private boolean pollAutoDisposal;
} 