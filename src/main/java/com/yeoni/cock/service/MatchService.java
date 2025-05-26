package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.match.MatchRequest;
import com.yeoni.cock.domain.entity.Match;
import com.yeoni.cock.mapper.MatchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchMapper mapper;

    public List<Match> listMatches() {
        return mapper.selectAll();
    }

    public Match getMatch(Integer id) {
        Match m = mapper.selectById(id);
        if (m == null) throw new IllegalArgumentException("Match not found: " + id);
        return m;
    }

    @Transactional
    public Match createMatch(MatchRequest req) {
        Match m = Match.builder()
            .clubId(req.getClubId())
            .userId(req.getUserId())
            .matchSdt(req.getMatchSdt())
            .matchEdt(req.getMatchEdt())
            .playerNm1(req.getPlayerNm1())
            .playerNm2(req.getPlayerNm2())
            .team1Score(req.getTeam1Score())
            .team1Result(req.getTeam1Result())
            .playerNm3(req.getPlayerNm3())
            .playerNm4(req.getPlayerNm4())
            .team2Score(req.getTeam2Score())
            .team2Result(req.getTeam2Result())
            .matchStat(req.getMatchStat())
            .build();
        mapper.insert(m);
        return mapper.selectById(m.getMatchKey());
    }

    @Transactional
    public void updateMatch(Integer id, MatchRequest req) {
        Match m = getMatch(id);
        m.setClubId(req.getClubId());
        m.setUserId(req.getUserId());
        m.setMatchSdt(req.getMatchSdt());
        m.setMatchEdt(req.getMatchEdt());
        m.setPlayerNm1(req.getPlayerNm1());
        m.setPlayerNm2(req.getPlayerNm2());
        m.setTeam1Score(req.getTeam1Score());
        m.setTeam1Result(req.getTeam1Result());
        m.setPlayerNm3(req.getPlayerNm3());
        m.setPlayerNm4(req.getPlayerNm4());
        m.setTeam2Score(req.getTeam2Score());
        m.setTeam2Result(req.getTeam2Result());
        m.setMatchStat(req.getMatchStat());
        mapper.update(m);
    }

    @Transactional
    public void deleteMatch(Integer id) {
        // 소프트 삭제
        mapper.softDelete(id);
    }
}
