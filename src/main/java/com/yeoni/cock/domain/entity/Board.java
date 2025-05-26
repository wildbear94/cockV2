package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private Long boardId;
    private Long clubId;
    private String name;
    private String description;
    private String status;
    private String type;
    private boolean isDeleted;
} 