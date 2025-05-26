package com.yeoni.cock.controller.poll;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.poll.PollItemCreateRequest;
import com.yeoni.cock.domain.dto.poll.PollItemResponse;
import com.yeoni.cock.domain.dto.poll.PollItemUpdateRequest;
import com.yeoni.cock.service.PollItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls/{pollId}/items")
@RequiredArgsConstructor
@Tag(name = "온라인 투표 아이템 API", description = "온라인 투표 아이템 관련 API")
public class PollItemController {

    private final PollItemService pollItemService;

    @Operation(summary = "온라인 투표 아이템 목록", description = "온라인 투표 아이템 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PollItemResponse>>> findByPollId(@PathVariable Long pollId) {
        return ResponseEntity.ok(ApiResponse.success(pollItemService.findByPollId(pollId)));
    }

    @Operation(summary = "온라인 투표 아이템 상세", description = "온라인 투표 아이템 상세")
    @GetMapping("/{itemId}")
    public ResponseEntity<ApiResponse<PollItemResponse>> findById(@PathVariable Long itemId) {
        return ResponseEntity.ok(ApiResponse.success(pollItemService.findById(itemId)));
    }

    @Operation(summary = "온라인 투표 아이템 생성", description = "온라인 투표 아이템 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @PathVariable Long pollId,
            @Valid @RequestBody PollItemCreateRequest request) {
        request.setPollId(pollId);
        pollItemService.create(request);
        return ResponseEntity.ok(ApiResponse.success("투표 항목이 생성되었습니다.", null));
    }

    @Operation(summary = "온라인 투표 아이템 수정", description = "온라인 투표 아이템 수정")
    @PutMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long itemId,
            @Valid @RequestBody PollItemUpdateRequest request) {
        pollItemService.update(itemId, request);
        return ResponseEntity.ok(ApiResponse.success("투표 항목이 수정되었습니다.", null));
    }

    @Operation(summary = "온라인 투표 아이템 삭제", description = "온라인 투표 아이템 삭제")
    @DeleteMapping("/{itemId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long itemId) {
        pollItemService.delete(itemId);
        return ResponseEntity.ok(ApiResponse.success("투표 항목이 삭제되었습니다.", null));
    }
} 