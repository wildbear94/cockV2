package com.yeoni.cock.controller.club;

import com.yeoni.cock.domain.dto.club.ClubCreateRequest;
import com.yeoni.cock.domain.dto.club.ClubResponse;
import com.yeoni.cock.domain.dto.club.ClubUpdateRequest;
import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.service.ClubService;
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
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
@Tag(name = "클럽 API", description = "클럽 관련 API")
public class ClubController {

    private final ClubService clubService;

    @Operation(summary = "클럽 목록", description = "클럽 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClubResponse>>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(clubService.findAll(page, size)));
    }

    @Operation(summary = "클럽 정보", description = "클럽 정보")
    @GetMapping("/{clubId}")
    public ResponseEntity<ApiResponse<ClubResponse>> findById(@PathVariable Long clubId) {
        return ResponseEntity.ok(ApiResponse.success(clubService.findById(clubId)));
    }

    @Operation(summary = "클럽 생성", description = "클럽 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @Valid @RequestBody ClubCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        clubService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("클럽이 생성되었습니다.", null));
    }

    @Operation(summary = "클럽 수정", description = "클럽 수정")
    @PutMapping("/{clubId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long clubId,
            @Valid @RequestBody ClubUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        clubService.update(clubId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("클럽 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "클럽 삭제", description = "클럽 삭제")
    @DeleteMapping("/{clubId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long clubId) {
        clubService.delete(clubId);
        return ResponseEntity.ok(ApiResponse.success("클럽이 삭제되었습니다.", null));
    }
} 