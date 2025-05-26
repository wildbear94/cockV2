package com.yeoni.cock.controller.teacher;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.teacher.TeacherCreateRequest;
import com.yeoni.cock.domain.dto.teacher.TeacherResponse;
import com.yeoni.cock.domain.dto.teacher.TeacherUpdateRequest;
import com.yeoni.cock.service.TeacherService;
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
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
@Tag(name = "레슨 홍보  API", description = "레슨 홍보 관련 API")
public class TeacherController {

    private final TeacherService teacherService;

    @Operation(summary = "레슨 홍보 목록", description = "레슨 홍보 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<TeacherResponse>>> findAll() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findAll()));
    }

    @Operation(summary = "레슨 홍보 상세", description = "레슨 홍보 상세")
    @GetMapping("/{teacherId}")
    public ResponseEntity<ApiResponse<TeacherResponse>> findById(
            @PathVariable Long teacherId,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(
                teacherService.findById(teacherId, userDetails.getUsername())));
    }

    @Operation(summary = "레슨 홍보 생성", description = "레슨 홍보 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @Valid @RequestBody TeacherCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        teacherService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("레슨 정보가 등록되었습니다.", null));
    }

    @Operation(summary = "레슨 홍보 수정", description = "레슨 홍보 수정")
    @PutMapping("/{teacherId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long teacherId,
            @Valid @RequestBody TeacherUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        teacherService.update(teacherId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("레슨 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "레슨 홍보 삭제", description = "레슨 홍보 삭제")
    @DeleteMapping("/{teacherId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long teacherId,
            @AuthenticationPrincipal UserDetails userDetails) {
        teacherService.delete(teacherId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("레슨 정보가 삭제되었습니다.", null));
    }

    @PutMapping("/{teacherId}/promotion")
    public ResponseEntity<ApiResponse<Void>> updatePromotionStatus(
            @PathVariable Long teacherId,
            @RequestParam String prYn,
            @AuthenticationPrincipal UserDetails userDetails) {
        teacherService.updatePromotionStatus(teacherId, userDetails.getUsername(), prYn);
        return ResponseEntity.ok(ApiResponse.success("홍보 상태가 변경되었습니다.", null));
    }

    @GetMapping("/promoted")
    public ResponseEntity<ApiResponse<List<TeacherResponse>>> findPromotedTeachers() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findPromotedTeachers()));
    }

    @GetMapping("/expired")
    public ResponseEntity<ApiResponse<List<TeacherResponse>>> findExpiredTeachers() {
        return ResponseEntity.ok(ApiResponse.success(teacherService.findExpiredTeachers()));
    }
} 