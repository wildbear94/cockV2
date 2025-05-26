package com.yeoni.cock.domain.dto.poll;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollResultResponse {
    private Long resultId;
    private Long pollId;
    private Long itemId;
    private String userId;
    private LocalDateTime votedAt;
    private String itemDescription;  // 투표 항목 설명
    private Integer itemOrder;       // 투표 항목 순서
} 