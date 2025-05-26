package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameInfo {
    private Integer seq;
    private String userId;
    private String gameName;
    private String gameScene;
    private String gameArea;
    private String gameAddrDoro;
    private String gameAddrJibun;
    private String gameAddrEtc;
    private LocalDateTime gameStDt;
    private LocalDateTime gameEdDt;
    private LocalDateTime gameReceiptStDt;
    private LocalDateTime gameReceiptEdDt;
    private String gameReceiptNum;
    private String atchFileId;
    private String gameContent;
    private LocalDateTime regDt;
    private LocalDateTime updDt;
    private String sponetId;
    private String imgSrc;
    private String gameStat;
} 