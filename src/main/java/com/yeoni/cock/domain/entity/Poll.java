package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Poll {
    private Long pollId;
    private Long clubId;
    private String pollName;
    private LocalDate pollStartDate;
    private LocalDate pollEndDate;
    private String pollType;  // MULTIPLE, RANKING, SINGLE
    private String pollDisposalFlag;  // ACTIVE, DISPOSED, INACTIVE
    private boolean pollAutoDisposal;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
} 