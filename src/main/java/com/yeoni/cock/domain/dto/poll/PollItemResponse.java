package com.yeoni.cock.domain.dto.poll;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollItemResponse {
    private Long itemId;
    private Long pollId;
    private String itemDescription;
    private Integer itemOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 