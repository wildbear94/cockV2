package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollResult {
    private Long resultId;
    private Long pollId;
    private Long itemId;
    private String userId;
    private LocalDateTime votedAt;
    private String itemDescription;
    private Integer itemOrder;
}
