package com.yeoni.cock.controller.market;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.market.MarketCreateRequest;
import com.yeoni.cock.domain.dto.market.MarketResponse;
import com.yeoni.cock.domain.dto.market.MarketUpdateRequest;
import com.yeoni.cock.service.MarketService;
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
@RequestMapping("/api/markets")
@RequiredArgsConstructor
@Tag(name = "스토어 홍보 API", description = "스토어 홍보 관련 API")
public class MarketController {

    private final MarketService marketService;

    @Operation(summary = "스토어 홍보 목록", description = "스토어 홍보 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MarketResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(marketService.findAll()));
    }

    @Operation(summary = "스토어 홍보 상세", description = "스토어 홍보 상세")
    @GetMapping("/{marketId}")
    public ResponseEntity<ApiResponse<MarketResponse>> findById(
            @PathVariable Long marketId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                marketService.findById(marketId, userDetails.getUsername())));
    }

    @Operation(summary = "스토어 홍보 생성", description = "스토어 홍보 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @Valid @RequestBody MarketCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        marketService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("스토어 정보가 등록되었습니다.", null));
    }

    @Operation(summary = "스토어 홍보 수정", description = "스토어 홍보 수정")
    @PutMapping("/{marketId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long marketId,
            @Valid @RequestBody MarketUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        marketService.update(marketId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("스토어 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "스토어 홍보 삭제", description = "스토어 홍보 삭제")
    @DeleteMapping("/{marketId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long marketId,
            @AuthenticationPrincipal UserDetails userDetails) {
        marketService.delete(marketId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("스토어 정보가 삭제되었습니다.", null));
    }
} 