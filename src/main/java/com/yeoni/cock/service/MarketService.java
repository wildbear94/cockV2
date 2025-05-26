package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.market.MarketCreateRequest;
import com.yeoni.cock.domain.dto.market.MarketResponse;
import com.yeoni.cock.domain.dto.market.MarketUpdateRequest;
import com.yeoni.cock.domain.entity.Market;
import com.yeoni.cock.mapper.MarketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final MarketMapper marketMapper;

    @Transactional(readOnly = true)
    public List<MarketResponse> findAll() {
        return marketMapper.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MarketResponse findById(Long marketId, String userId) {
        Market market = marketMapper.findById(marketId, userId)
                .orElseThrow(() -> new RuntimeException("스토어 정보를 찾을 수 없습니다."));
        return convertToResponse(market);
    }

    @Transactional
    public void create(MarketCreateRequest request, String userId) {
        Market market = new Market();
        market.setUserId(userId);
        market.setMarketNm(request.getMarketNm());
        market.setMainPhoto(request.getMainPhoto());
        market.setMarketNum(request.getMarketNum());
        market.setMarketAddrDoro(request.getMarketAddrDoro());
        market.setMarketAddrJibun(request.getMarketAddrJibun());
        market.setMarketAddrEtc(request.getMarketAddrEtc());
        market.setMarketUrl(request.getMarketUrl());
        market.setSubPhoto(request.getSubPhoto());
        market.setMarketText(request.getMarketText());
        market.setPrYn("n"); // 기본값은 홍보 미승인
        market.setDelYn("n"); // 기본값은 삭제되지 않음
        market.setRegDt(LocalDateTime.now()); // 등록일 자동 설정
        market.setEntDt(request.getEntDt());

        marketMapper.save(market);
    }

    @Transactional
    public void update(Long marketId, MarketUpdateRequest request, String userId) {
        Market market = marketMapper.findById(marketId, userId)
                .orElseThrow(() -> new RuntimeException("스토어 정보를 찾을 수 없습니다."));

        market.setMarketNm(request.getMarketNm());
        market.setMainPhoto(request.getMainPhoto());
        market.setMarketNum(request.getMarketNum());
        market.setMarketAddrDoro(request.getMarketAddrDoro());
        market.setMarketAddrJibun(request.getMarketAddrJibun());
        market.setMarketAddrEtc(request.getMarketAddrEtc());
        market.setMarketUrl(request.getMarketUrl());
        market.setSubPhoto(request.getSubPhoto());
        market.setMarketText(request.getMarketText());
        market.setPrYn(request.getPrYn());
        market.setEntDt(request.getEntDt());
        market.setUpdDt(LocalDateTime.now()); // 수정일 자동 설정

        marketMapper.update(market);
    }

    @Transactional
    public void delete(Long marketId, String userId) {
        Market market = marketMapper.findById(marketId, userId)
                .orElseThrow(() -> new RuntimeException("스토어 정보를 찾을 수 없습니다."));
        marketMapper.delete(marketId, userId);
    }

    @Transactional
    public void updatePromotionStatus(Long marketId, String userId, String prYn) {
        Market market = marketMapper.findById(marketId, userId)
                .orElseThrow(() -> new RuntimeException("스토어 정보를 찾을 수 없습니다."));
        
        market.setPrYn(prYn);
        market.setUpdDt(LocalDateTime.now());
        marketMapper.update(market);
    }

    @Transactional(readOnly = true)
    public List<MarketResponse> findPromotedMarkets() {
        return marketMapper.findPromotedMarkets().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MarketResponse> findExpiredMarkets() {
        LocalDate today = LocalDate.now();
        return marketMapper.findExpiredMarkets(today).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private MarketResponse convertToResponse(Market market) {
        MarketResponse response = new MarketResponse();
        response.setMarketId(market.getMarketId());
        response.setUserId(market.getUserId());
        response.setMarketNm(market.getMarketNm());
        response.setMainPhoto(market.getMainPhoto());
        response.setMarketNum(market.getMarketNum());
        response.setMarketAddrDoro(market.getMarketAddrDoro());
        response.setMarketAddrJibun(market.getMarketAddrJibun());
        response.setMarketAddrEtc(market.getMarketAddrEtc());
        response.setMarketUrl(market.getMarketUrl());
        response.setSubPhoto(market.getSubPhoto());
        response.setMarketText(market.getMarketText());
        response.setPrYn(market.getPrYn());
        response.setDelYn(market.getDelYn());
        response.setRegDt(market.getRegDt());
        response.setUpdDt(market.getUpdDt());
        response.setEntDt(market.getEntDt());
        return response;
    }
} 