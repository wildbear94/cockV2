package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.game.GameInfoCreateRequest;
import com.yeoni.cock.domain.dto.game.GameInfoResponse;
import com.yeoni.cock.domain.dto.game.GameInfoUpdateRequest;
import com.yeoni.cock.domain.entity.GameInfo;
import com.yeoni.cock.mapper.GameInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameInfoService {

    private final GameInfoMapper gameInfoMapper;

    @Transactional(readOnly = true)
    public List<GameInfoResponse> findAll() {
        return gameInfoMapper.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public GameInfoResponse findById(Integer seq, String userId) {
        GameInfo gameInfo = gameInfoMapper.findById(seq, userId)
                .orElseThrow(() -> new RuntimeException("대회 정보를 찾을 수 없습니다."));
        return convertToResponse(gameInfo);
    }

    @Transactional
    public void create(GameInfoCreateRequest request, String userId) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setUserId(userId);
        gameInfo.setGameName(request.getGameName());
        gameInfo.setGameScene(request.getGameScene());
        gameInfo.setGameArea(request.getGameArea());
        gameInfo.setGameAddrDoro(request.getGameAddrDoro());
        gameInfo.setGameAddrJibun(request.getGameAddrJibun());
        gameInfo.setGameAddrEtc(request.getGameAddrEtc());
        gameInfo.setGameStDt(request.getGameStDt());
        gameInfo.setGameEdDt(request.getGameEdDt());
        gameInfo.setGameReceiptStDt(request.getGameReceiptStDt());
        gameInfo.setGameReceiptEdDt(request.getGameReceiptEdDt());
        gameInfo.setGameReceiptNum(request.getGameReceiptNum());
        gameInfo.setAtchFileId(request.getAtchFileId());
        gameInfo.setGameContent(request.getGameContent());
        gameInfo.setSponetId(request.getSponetId());
        gameInfo.setImgSrc(request.getImgSrc());
        gameInfo.setGameStat(request.getGameStat());

        gameInfoMapper.save(gameInfo);
    }

    @Transactional
    public void update(Integer seq, GameInfoUpdateRequest request, String userId) {
        GameInfo gameInfo = gameInfoMapper.findById(seq, userId)
                .orElseThrow(() -> new RuntimeException("대회 정보를 찾을 수 없습니다."));

        gameInfo.setGameName(request.getGameName());
        gameInfo.setGameScene(request.getGameScene());
        gameInfo.setGameArea(request.getGameArea());
        gameInfo.setGameAddrDoro(request.getGameAddrDoro());
        gameInfo.setGameAddrJibun(request.getGameAddrJibun());
        gameInfo.setGameAddrEtc(request.getGameAddrEtc());
        gameInfo.setGameStDt(request.getGameStDt());
        gameInfo.setGameEdDt(request.getGameEdDt());
        gameInfo.setGameReceiptStDt(request.getGameReceiptStDt());
        gameInfo.setGameReceiptEdDt(request.getGameReceiptEdDt());
        gameInfo.setGameReceiptNum(request.getGameReceiptNum());
        gameInfo.setAtchFileId(request.getAtchFileId());
        gameInfo.setGameContent(request.getGameContent());
        gameInfo.setSponetId(request.getSponetId());
        gameInfo.setImgSrc(request.getImgSrc());
        gameInfo.setGameStat(request.getGameStat());

        gameInfoMapper.update(gameInfo);
    }

    @Transactional
    public void delete(Integer seq, String userId) {
        GameInfo gameInfo = gameInfoMapper.findById(seq, userId)
                .orElseThrow(() -> new RuntimeException("대회 정보를 찾을 수 없습니다."));
        gameInfoMapper.delete(seq, userId);
    }

    private GameInfoResponse convertToResponse(GameInfo gameInfo) {
        GameInfoResponse response = new GameInfoResponse();
        response.setSeq(gameInfo.getSeq());
        response.setUserId(gameInfo.getUserId());
        response.setGameName(gameInfo.getGameName());
        response.setGameScene(gameInfo.getGameScene());
        response.setGameArea(gameInfo.getGameArea());
        response.setGameAddrDoro(gameInfo.getGameAddrDoro());
        response.setGameAddrJibun(gameInfo.getGameAddrJibun());
        response.setGameAddrEtc(gameInfo.getGameAddrEtc());
        response.setGameStDt(gameInfo.getGameStDt());
        response.setGameEdDt(gameInfo.getGameEdDt());
        response.setGameReceiptStDt(gameInfo.getGameReceiptStDt());
        response.setGameReceiptEdDt(gameInfo.getGameReceiptEdDt());
        response.setGameReceiptNum(gameInfo.getGameReceiptNum());
        response.setAtchFileId(gameInfo.getAtchFileId());
        response.setGameContent(gameInfo.getGameContent());
        response.setRegDt(gameInfo.getRegDt());
        response.setUpdDt(gameInfo.getUpdDt());
        response.setSponetId(gameInfo.getSponetId());
        response.setImgSrc(gameInfo.getImgSrc());
        response.setGameStat(gameInfo.getGameStat());
        return response;
    }
} 