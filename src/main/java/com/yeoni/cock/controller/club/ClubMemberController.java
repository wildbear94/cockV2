package com.yeoni.cock.controller.club;

import com.yeoni.cock.domain.dto.club.ClubMemberResponse;
import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.service.ClubMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs/{clubId}/members")
@RequiredArgsConstructor
@Tag(name = "클럽 회원 API", description = "클럽 회원 관련 API")
public class ClubMemberController {

    private final ClubMemberService clubMemberService;

    @Operation(summary = "클럽 멥버 목록", description = "클럽 멤버 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ClubMemberResponse>>> findByClubId(@PathVariable Long clubId) {
        return ResponseEntity.ok(ApiResponse.success(clubMemberService.findByClubId(clubId)));
    }

    @Operation(summary = "마이 클럽 목록", description = "마이 클럽 목록")
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<ClubMemberResponse>>> findByUserId(
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(clubMemberService.findByUserId(userDetails.getUsername())));
    }

    @Operation(summary = "클럽 가입", description = "클럽 가입")
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<Void>> join(
            @PathVariable Long clubId,
            @AuthenticationPrincipal UserDetails userDetails) {
        clubMemberService.join(clubId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("클럽에 가입되었습니다.", null));
    }

    @Operation(summary = "클럽 탈퇴", description = "클럽 탈퇴")
    @PostMapping("/leave")
    public ResponseEntity<ApiResponse<Void>> leave(
            @PathVariable Long clubId,
            @AuthenticationPrincipal UserDetails userDetails) {
        clubMemberService.leave(clubId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("클럽에서 탈퇴되었습니다.", null));
    }
} 