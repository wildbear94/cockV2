package com.yeoni.cock.domain.dto.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherCreateRequest {
    @NotBlank(message = "주사진은 필수 입력값입니다.")
    private String mainPhoto;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    private String phoneNumber;

    @NotBlank(message = "도로명 주소는 필수 입력값입니다.")
    private String lessonAddrDoro;

    private String lessonAddrJibun;
    private String lessonAddrEtc;
    private String subPhoto;

    @NotBlank(message = "이력은 필수 입력값입니다.")
    private String careerText;

    private String localParkingYn;
    private String localBathroomYn;
    private String localShowerYn;
    private String localColdHeatYn;
    private String localDressYn;

    @NotNull(message = "종료일은 필수 입력값입니다.")
    private LocalDate entDt;
} 