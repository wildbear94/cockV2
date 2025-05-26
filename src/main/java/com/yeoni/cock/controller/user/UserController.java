package com.yeoni.cock.controller.user;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.user.UserDetailResponse;
import com.yeoni.cock.domain.dto.user.UserListResponse;
import com.yeoni.cock.domain.dto.user.UserUpdateRequest;
import com.yeoni.cock.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "회원 API", description = "회원 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 목록", description = "회원 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserListResponse>>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(userService.findAll(page, size)));
    }

    @Operation(summary = "회원 상세", description = "회원 상세")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDetailResponse>> findById(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.success(userService.findById(userId)));
    }

    @Operation(summary = "회원 수정", description = "회원 수정")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable String userId,
            @Valid @RequestBody UserUpdateRequest request) {
        userService.update(userId, request);
        return ResponseEntity.ok(ApiResponse.success("회원 정보가 수정되었습니다.", null));
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String userId) {
        userService.delete(userId);
        return ResponseEntity.ok(ApiResponse.success("회원이 삭제되었습니다.", null));
    }
} 