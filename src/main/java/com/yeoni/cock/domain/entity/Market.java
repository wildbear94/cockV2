package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Market {
    private Long marketId;
    private String userId;
    private String marketNm;
    private String mainPhoto;
    private String marketNum;
    private String marketAddrDoro;
    private String marketAddrJibun;
    private String marketAddrEtc;
    private String marketUrl;
    private String subPhoto;
    private String marketText;
    private String prYn;
    private String delYn;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private LocalDate entDt;
} 