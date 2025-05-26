package com.yeoni.cock.controller.poll;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.poll.PollResultCreateRequest;
import com.yeoni.cock.domain.dto.poll.PollResultResponse;
import com.yeoni.cock.service.PollResultService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/polls/{pollId}/results")
@RequiredArgsConstructor
@Tag(name = "온라인 투표 결과 API", description = "온라인 투표 결과 관련 API")
public class PollResultController {

    private final PollResultService pollResultService;

    @Operation(summary = "투표 결과 목록", description = "투표 결과 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PollResultResponse>>> findByPollId(@PathVariable Long pollId) {
        return ResponseEntity.ok(ApiResponse.success(pollResultService.findByPollId(pollId)));
    }

    @Operation(summary = " 나의 투표 결과", description = "나의 투표 결과")
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<PollResultResponse>>> findByUserId(
            @PathVariable Long pollId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                pollResultService.findByUserId(pollId, userDetails.getUsername())));
    }

    @Operation(summary = "투표 상태", description = "투표 상태")
    @GetMapping("/statistics")
    public ResponseEntity<ApiResponse<Map<Long, Integer>>> getStatistics(@PathVariable Long pollId) {
        return ResponseEntity.ok(ApiResponse.success(pollResultService.getPollStatistics(pollId)));
    }

    @Operation(summary = "투표", description = "투표")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @PathVariable Long pollId,
            @Valid @RequestBody PollResultCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        request.setPollId(pollId);
        pollResultService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("투표가 완료되었습니다.", null));
    }

    @Operation(summary = "투표 취소", description = "투표 취소")
    @DeleteMapping("/{resultId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long resultId,
            @AuthenticationPrincipal UserDetails userDetails) {
        pollResultService.delete(resultId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("투표가 취소되었습니다.", null));
    }
} 