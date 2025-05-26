package com.yeoni.cock.domain.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResponse {
    private Long boardId;
    private Long clubId;
    private String name;
    private String description;
    private String status;
    private String type;
} 