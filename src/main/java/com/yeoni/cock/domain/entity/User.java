package com.yeoni.cock.domain.entity;

import com.yeoni.cock.domain.enums.UserRole;
import com.yeoni.cock.domain.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class User {
    private String userId;                          // 사용자 고유 ID
    private String username;                        // 로그인용 아이디
    private String password;                        // 암호화된 비밀번호 해시
    private String email;                           // 이메일 주소
    private UserStatus status = UserStatus.ACTIVE;  // 계정 상태
    private String nickname;                        // 사용자 별명
    private LocalDate birthDate;                    // 생년월일
    private boolean isLunar = false;                // 음양력 여부
    private String identityVerificationId;          // 본인인증 시스템에서 발급된 ID
    private String appId;                           // 앱 고유 식별자
    private Byte isReceiveNotification = 1;         // 알림 수신 여부 (1=수신, 0=거부)
    private String simpleSignupProvider;            // 간편가입 제공자
    private UserRole role = UserRole.USER;          // 사용자 권한 역할
    private LocalDateTime withdrawalDatetime;       // 회원 탈퇴 처리 일시
    private LocalDateTime membershipEndDate;        // 가입 종료 일시
    private String phoneNumber;                     // 전화번호
    private String postalCode;                      // 우편번호
    private String address;                         // 주소
    private String addressDetail;                   // 상세 주소
    private LocalDateTime createdAt = LocalDateTime.now();  // 가입일시
    private boolean isDeleted = false;              // 소프트 딜리트 플래그
}