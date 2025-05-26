package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Market;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MarketMapper {
    List<Market> findAll();
    Optional<Market> findById(@Param("marketId") Long marketId, @Param("userId") String userId);
    void save(@Param("market") Market market);
    void update(@Param("market") Market market);
    void delete(@Param("marketId") Long marketId, @Param("userId") String userId);
    List<Market> findPromotedMarkets();
    List<Market> findExpiredMarkets(@Param("today") LocalDate today);
} 