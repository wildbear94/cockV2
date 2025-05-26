package com.yeoni.cock.domain.dto.auth;

import com.yeoni.cock.domain.dto.file.FileInfoResponse;
import com.yeoni.cock.domain.dto.user.UserDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;
    private UserDetailResponse userDetailResponse;
    private FileInfoResponse fileInfoResponse;
} 