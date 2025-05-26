package com.yeoni.cock.domain.dto.club;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubCreateRequest {
    @NotBlank(message = "클럽명은 필수 입력값입니다.")
    @Size(max = 100, message = "클럽명은 100자를 초과할 수 없습니다.")
    private String name;

    private String description;

    private String address;
    private String addressDetail;

    @NotNull(message = "활동여부는 필수 입력값입니다.")
    private boolean isActive;

    private String introduction;

    @NotBlank(message = "문의 담당자 이름은 필수 입력값입니다.")
    @Size(max = 100, message = "문의 담당자 이름은 100자를 초과할 수 없습니다.")
    private String inquiryContact;

    @NotBlank(message = "문의 전화번호는 필수 입력값입니다.")
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다.")
    private String inquiryPhone;

    @NotNull(message = "홍보여부는 필수 입력값입니다.")
    private boolean isPromoted;

    @NotNull(message = "공개여부는 필수 입력값입니다.")
    private boolean isPublic;

    @NotNull(message = "최대 회원수는 필수 입력값입니다.")
    private int maxMembers;
} 