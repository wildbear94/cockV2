package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PollItem {
    private Long itemId;
    private Long pollId;
    private String itemDescription;
    private Integer itemOrder;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 