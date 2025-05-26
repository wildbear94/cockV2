package com.yeoni.cock.domain.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int code;
    private String reason;

    public ErrorResponse(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

}
