package com.yeoni.cock.domain.dto.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameInfoCreateRequest {
    @NotBlank(message = "대회 명칭은 필수 입력값입니다.")
    private String gameName;

    @NotBlank(message = "대회 종류는 필수 입력값입니다.")
    private String gameScene;

    @NotBlank(message = "대회 장소는 필수 입력값입니다.")
    private String gameArea;

    private String gameAddrDoro;
    private String gameAddrJibun;
    private String gameAddrEtc;

    @NotNull(message = "대회 시작일은 필수 입력값입니다.")
    private LocalDateTime gameStDt;

    @NotNull(message = "대회 종료일은 필수 입력값입니다.")
    private LocalDateTime gameEdDt;

    @NotNull(message = "접수 시작일은 필수 입력값입니다.")
    private LocalDateTime gameReceiptStDt;

    @NotNull(message = "접수 종료일은 필수 입력값입니다.")
    private LocalDateTime gameReceiptEdDt;

    @NotBlank(message = "문의 전화번호는 필수 입력값입니다.")
    private String gameReceiptNum;

    private String atchFileId;
    private String gameContent;
    private String sponetId;
    private String imgSrc;
    private String gameStat;
} 