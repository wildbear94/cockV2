package com.yeoni.cock.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSignUpRequest {
    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Size(min = 3, max = 100, message = "아이디는 3~100자 사이여야 합니다.")
    private String userId;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    @Size(min = 3, max = 50, message = "이름은 3~50자 사이여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    @Size(min = 4, max = 100, message = "비밀번호는 4~100자 사이여야 합니다.")
    private String password;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(max = 50, message = "닉네임은 50자를 초과할 수 없습니다.")
    private String nickname;

    private LocalDate birthDate;
    private boolean isLunar;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{5}$", message = "우편번호 형식이 올바르지 않습니다.")
    private String postalCode;

    @Size(max = 255, message = "주소는 255자를 초과할 수 없습니다.")
    private String address;

    @Size(max = 255, message = "상세주소는 255자를 초과할 수 없습니다.")
    private String addressDetail;

    @Size(max = 200, message = "앱 ID는 200자를 초과할 수 없습니다.")
    private String appId;

    private Byte isReceiveNotification = 1; // 알림 수신 여부 (1=수신, 0=거부)

    @Size(max = 50, message = "간편가입 제공자는 50자를 초과할 수 없습니다.")
    private String simpleSignupProvider;
}