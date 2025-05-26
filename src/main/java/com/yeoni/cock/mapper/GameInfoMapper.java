package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.GameInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GameInfoMapper {
    List<GameInfo> findAll();
    Optional<GameInfo> findById(@Param("seq") Integer seq, @Param("userId") String userId);
    void save(@Param("gameInfo") GameInfo gameInfo);
    void update(@Param("gameInfo") GameInfo gameInfo);
    void delete(@Param("seq") Integer seq, @Param("userId") String userId);
} 