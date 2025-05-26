package com.yeoni.cock.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Match {

    private Integer matchKey;      // 매치 키값 (자동 증가)
    private String clubId;         // 클럽 ID
    private String userId;         // 게임 심판 또는 작성자 (user ID)
    private LocalDateTime matchSdt;// 매치시작 년월일시분
    private LocalDateTime matchEdt;// 매치종료년월일시분
    private String playerNm1;      // 선수1
    private String playerNm2;      // 선수2
    private Integer team1Score;    // team1 점수
    private String team1Result;    // team1 승패
    private String playerNm3;      // 선수3
    private String playerNm4;      // 선수4
    private Integer team2Score;    // team2 점수
    private String team2Result;    // team2 승패
    private String matchStat;      // 경기상태(y:경기중 n:경기종료)
    private String delYn;          // 삭제여부

}
