package com.yeoni.cock.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {
    @Size(max = 50, message = "닉네임은 50자를 초과할 수 없습니다.")
    private String nickname;

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

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
} 