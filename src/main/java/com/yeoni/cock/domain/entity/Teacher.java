package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Teacher {
    private Long teacherId;
    private String userId;
    private String mainPhoto;
    private String phoneNumber;
    private String lessonAddrDoro;
    private String lessonAddrJibun;
    private String lessonAddrEtc;
    private String subPhoto;
    private String careerText;
    private String localParkingYn;
    private String localBathroomYn;
    private String localShowerYn;
    private String localColdHeatYn;
    private String localDressYn;
    private String prYn;
    private String delYn;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private LocalDate entDt;
} 