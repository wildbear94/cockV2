package com.yeoni.cock.domain.dto.user;

import com.yeoni.cock.domain.enums.UserRole;
import com.yeoni.cock.domain.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserListResponse {
    private String userId;
    private String username;
    private String email;
    private String nickname;
    private UserStatus status;
    private UserRole role;
    private LocalDate birthDate;
    private boolean lunar;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
} 