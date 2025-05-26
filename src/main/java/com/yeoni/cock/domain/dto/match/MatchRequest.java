package com.yeoni.cock.domain.dto.match;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchRequest {

    private String clubId;
    private String userId;
    private LocalDateTime matchSdt;
    private LocalDateTime matchEdt;
    private String playerNm1;
    private String playerNm2;
    private Integer team1Score;
    private String team1Result;
    private String playerNm3;
    private String playerNm4;
    private Integer team2Score;
    private String team2Result;
    private String matchStat;
}
