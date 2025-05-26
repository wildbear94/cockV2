package com.yeoni.cock.domain.dto.poll;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PollResponse {
    private Long pollId;
    private Long clubId;
    private String pollName;
    private LocalDate pollStartDate;
    private LocalDate pollEndDate;
    private String pollType;
    private String pollDisposalFlag;
    private boolean pollAutoDisposal;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
} 