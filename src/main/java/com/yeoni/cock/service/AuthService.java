package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.user.UserSignUpRequest;
import com.yeoni.cock.domain.dto.auth.LoginRequest;
import com.yeoni.cock.domain.dto.auth.TokenResponse;
import com.yeoni.cock.domain.entity.User;
import com.yeoni.cock.domain.entity.RefreshToken;
import com.yeoni.cock.domain.enums.UserRole;
import com.yeoni.cock.domain.enums.UserStatus;
import com.yeoni.cock.mapper.UserMapper;
import com.yeoni.cock.mapper.RefreshTokenMapper;
import com.yeoni.cock.security.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenMapper refreshTokenMapper;

    @Transactional
    public void signUp(UserSignUpRequest request) {

        // 아이디 중복 체크
        if (userMapper.findByUserId(request.getUserId()).isPresent()) {
            throw new RuntimeException("이미 사용 중인 아이디 입니다.");
        }

        // 이메일 중복 체크
        if (userMapper.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        User user = new User();
        user.setUserId(request.getUserId());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setStatus(UserStatus.ACTIVE);
        user.setNickname(request.getNickname());
        user.setBirthDate(request.getBirthDate());
        user.setLunar(request.isLunar());
        user.setRole(UserRole.USER);
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPostalCode(request.getPostalCode());
        user.setAddress(request.getAddress());
        user.setAddressDetail(request.getAddressDetail());

        userMapper.save(user);
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword())
        );

        String accessToken = tokenProvider.CreateToken(authentication, "access");
        String refreshToken = tokenProvider.CreateToken(authentication, "refresh");

        // 기존 리프레시 토큰 삭제
        refreshTokenMapper.deleteByUserId(request.getUserId());

        // 새 리프레시 토큰 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setTokenId(UUID.randomUUID().toString());
        refreshTokenEntity.setUserId(request.getUserId());
        refreshTokenEntity.setToken(refreshToken);
        refreshTokenEntity.setCreatedAt(LocalDateTime.now());
        refreshTokenEntity.setExpiresAt(LocalDateTime.now().plusSeconds(86400)); // 24시간
        refreshTokenEntity.setRevoked(false);
        refreshTokenMapper.save(refreshTokenEntity);

        //appid 업데이트
        if(request.getAppId() != null){
            userMapper.updateAppId(request);
        }


        return new TokenResponse(accessToken, refreshToken, "Bearer", 86400);
    }

    @Transactional
    public TokenResponse refreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 리프레시 토큰입니다.");
        }

        String userId = tokenProvider.getUsername(refreshToken);


        User user = userMapper.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            user.getUserId(), null, null
        );

        String newAccessToken = tokenProvider.CreateToken(authentication, "access");
        String newRefreshToken = tokenProvider.CreateToken(authentication, "refresh");

        return new TokenResponse(newAccessToken, newRefreshToken, "Bearer", 7776000);
    }

    @Transactional
    public void logout(String accessToken) {
        // accessToken에서 userId 추출
        String userId = tokenProvider.getUsername(accessToken.replace("Bearer ", "").trim());
        // 해당 유저의 리프레시 토큰 삭제
        refreshTokenMapper.deleteByUserId(userId);
    }
} 