package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.RefreshToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface RefreshTokenMapper {
    void save(@Param("refreshToken") RefreshToken refreshToken);
    Optional<RefreshToken> findByToken(@Param("token") String token);
    Optional<RefreshToken> findByUserId(@Param("userId") String userId);
    void revoke(@Param("tokenId") String tokenId);
    void deleteByUserId(@Param("userId") String userId);
} 