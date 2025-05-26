package com.yeoni.cock.domain.dto.market;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MarketUpdateRequest {
    @NotBlank(message = "스토어명은 필수 입력값입니다.")
    private String marketNm;

    @NotBlank(message = "주사진은 필수 입력값입니다.")
    private String mainPhoto;

    @NotBlank(message = "전화번호는 필수 입력값입니다.")
    private String marketNum;

    @NotBlank(message = "도로명 주소는 필수 입력값입니다.")
    private String marketAddrDoro;

    private String marketAddrJibun;
    private String marketAddrEtc;
    private String marketUrl;
    private String subPhoto;

    @NotBlank(message = "스토어 내용은 필수 입력값입니다.")
    private String marketText;

    @NotNull(message = "종료일은 필수 입력값입니다.")
    private LocalDate entDt;

    private String prYn;
} 