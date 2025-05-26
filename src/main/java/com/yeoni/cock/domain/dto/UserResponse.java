package com.yeoni.cock.domain.dto;

import com.yeoni.cock.domain.enums.UserRole;
import com.yeoni.cock.domain.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private String userId;
    private String username;
    private String email;
    private UserStatus status;
    private String nickname;
    private LocalDate birthDate;
    private boolean isLunar;
    private UserRole role;
    private String phoneNumber;
    private String postalCode;
    private String address;
    private String addressDetail;
    private LocalDateTime createdAt;
} 