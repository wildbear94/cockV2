package com.yeoni.cock.controller.game;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.game.GameInfoCreateRequest;
import com.yeoni.cock.domain.dto.game.GameInfoResponse;
import com.yeoni.cock.domain.dto.game.GameInfoUpdateRequest;
import com.yeoni.cock.service.GameInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
@Tag(name = "대회 API", description = "대회 관련 API")
public class GameInfoController {

    private final GameInfoService gameInfoService;

    @Operation(summary = "대회 목록", description = "대회 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<GameInfoResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(gameInfoService.findAll()));
    }

    @Operation(summary = "대회 상세", description = "대회 상세")
    @GetMapping("/{seq}")
    public ResponseEntity<ApiResponse<GameInfoResponse>> findById(
            @PathVariable Integer seq,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                gameInfoService.findById(seq, userDetails.getUsername())));
    }

    @Operation(summary = "대회 생성", description = "대회 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @Valid @RequestBody GameInfoCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        gameInfoService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("대회 정보가 등록되었습니다.", null));
    }

    @Operation(summary = "대회 정보 변경", description = "대회 정보 변경")
    @PutMapping("/{seq}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Integer seq,
            @Valid @RequestBody GameInfoUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        gameInfoService.update(seq, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("대회 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "대회 정보 삭제", description = "대회 정보 삭제")
    @DeleteMapping("/{seq}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer seq,
            @AuthenticationPrincipal UserDetails userDetails) {
        gameInfoService.delete(seq, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("대회 정보가 삭제되었습니다.", null));
    }
} 