package com.yeoni.cock.controller.poll;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.poll.PollCreateRequest;
import com.yeoni.cock.domain.dto.poll.PollResponse;
import com.yeoni.cock.domain.dto.poll.PollUpdateRequest;
import com.yeoni.cock.service.PollService;
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
@RequestMapping("/api/clubs/{clubId}/polls")
@RequiredArgsConstructor
@Tag(name = "온라인 투표 API", description = "온라인 투표 관련 API")
public class PollController {

    private final PollService pollService;

    @Operation(summary = "온라인 투표 목록", description = "온라인 투표 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PollResponse>>> findByClubId(@PathVariable Long clubId) {
        return ResponseEntity.ok(ApiResponse.success(pollService.findByClubId(clubId)));
    }

    @Operation(summary = "온라인 투표 상세", description = "온라인 투표 상세")
    @GetMapping("/{pollId}")
    public ResponseEntity<ApiResponse<PollResponse>> findById(@PathVariable Long pollId) {
        return ResponseEntity.ok(ApiResponse.success(pollService.findById(pollId)));
    }

    @Operation(summary = "온라인 투표 생성", description = "온라인 투표 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @PathVariable Long clubId,
            @Valid @RequestBody PollCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        request.setClubId(clubId);
        pollService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("투표가 생성되었습니다.", null));
    }

    @Operation(summary = "온라인 투표 수정", description = "온라인 투표 수정")
    @PutMapping("/{pollId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long pollId,
            @Valid @RequestBody PollUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        pollService.update(pollId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("투표가 수정되었습니다.", null));
    }

    @Operation(summary = "온라인 투표 삭제", description = "온라인 투표 삭제")
    @DeleteMapping("/{pollId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long pollId) {
        pollService.delete(pollId);
        return ResponseEntity.ok(ApiResponse.success("투표가 삭제되었습니다.", null));
    }
} 