package com.yeoni.cock.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String nickname;

    private LocalDate birthDate;
    private boolean lunar;

    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNumber;

    @Pattern(regexp = "^\\d{5}$", message = "우편번호 형식이 올바르지 않습니다.")
    private String postalCode;

    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String address;
    private String addressDetail;
} 