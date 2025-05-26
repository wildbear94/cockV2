package com.yeoni.cock.controller.auth;

import com.yeoni.cock.domain.dto.auth.LoginRequest;
import com.yeoni.cock.domain.dto.auth.TokenResponse;
import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.user.UserSignUpRequest;
import com.yeoni.cock.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증 API", description = "인증 관련 API")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원 등록", description = "새로운 회원을 등록합니다")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signUp(@Valid @RequestBody UserSignUpRequest request) {
        authService.signUp(request);
        return ResponseEntity.ok(ApiResponse.success("회원가입이 완료되었습니다.", null));
    }

    @Operation(summary = "로그인", description = "회원 로그인 하여 jwt 발급")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(request)));
    }

    @Operation(summary = "리플래쉬토큰", description = "리플래쉬토큰 으로 jwt 발급")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<TokenResponse>> refresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return ResponseEntity.ok(ApiResponse.success(authService.refreshToken(refreshToken)));
    }

    @Operation(summary = "로그아웃", description = "jwt 삭제함")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken);
        return ResponseEntity.ok(ApiResponse.success("로그아웃되었습니다.", null));
    }
}