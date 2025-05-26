package com.yeoni.cock.controller.post;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.post.PostReportRequest;
import com.yeoni.cock.domain.entity.PostReport;
import com.yeoni.cock.service.PostReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "게시글 신고 API", description = "게시글 신고 관련 API")
public class PostReportController {

    private final PostReportService reportService;

    @PostMapping
    @Operation(summary = "신고글 등록", description = "신고글 등록")
    public ResponseEntity<ApiResponse<Void>> report(@RequestBody PostReportRequest dto) {
        reportService.reportPost(dto);
        //return ResponseEntity.ok().build();
        return ResponseEntity.ok(ApiResponse.success("게시글 신고가 등록되었습니다.", null));
    }

    @GetMapping
    @Operation(summary = "신고글 목록", description = "신고글 목록")
    public ResponseEntity<ApiResponse<List<PostReport>>> getReports() {
        return ResponseEntity.ok(ApiResponse.success("게시글 신고 목록 조회 성공", reportService.getAllReports()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "신고글 상세", description = "신고글 상세")
    public ResponseEntity<ApiResponse<PostReport>> getReport(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("신고글 조회 성공",reportService.getReport(id)));
    }

    @PutMapping("/{id}")
    @Operation(summary = "신고글 수정", description = "신고글 수정")
    public ResponseEntity<ApiResponse<Void>> updateReason(@PathVariable Long id, @RequestParam String reason) {
        reportService.updateReport(id, reason);
        return ResponseEntity.ok(ApiResponse.success("게시글 신고가 수정 되었습니다.", null));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "신고글 삭제", description = "신고글 삭제")
    public ResponseEntity<ApiResponse<Void>> deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok(ApiResponse.success("게시글 신고가 삭제 되었습니다.", null));
    }

}
