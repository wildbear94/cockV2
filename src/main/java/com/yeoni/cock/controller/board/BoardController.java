package com.yeoni.cock.controller.board;

import com.yeoni.cock.domain.dto.board.BoardCreateRequest;
import com.yeoni.cock.domain.dto.board.BoardResponse;
import com.yeoni.cock.domain.dto.board.BoardUpdateRequest;
import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.service.BoardService;
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
@RequestMapping("/api/clubs/{clubId}/boards")
@RequiredArgsConstructor
@Tag(name = "게시판 API", description = "게시판 관련 API")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판목록", description = "클럽 게시판 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<BoardResponse>>> findByClubId(@PathVariable Long clubId) {
        return ResponseEntity.ok(ApiResponse.success(boardService.findByClubId(clubId)));
    }

    @Operation(summary = "게시판 정보", description = "게시판 정보")
    @GetMapping("/{boardId}")
    public ResponseEntity<ApiResponse<BoardResponse>> findById(@PathVariable Long boardId) {
        return ResponseEntity.ok(ApiResponse.success(boardService.findById(boardId)));
    }

    @Operation(summary = "게시판 생성", description = "클럽 게시판 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @PathVariable Long clubId,
            @Valid @RequestBody BoardCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        request.setClubId(clubId);
        boardService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시판이 생성되었습니다.", null));
    }

    @Operation(summary = "게시판 정보 수정", description = "클럽 게시판 정보 수정")
    @PutMapping("/{boardId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long boardId,
            @Valid @RequestBody BoardUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        boardService.update(boardId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시판이 수정되었습니다.", null));
    }

    @Operation(summary = "게시판 삭제", description = "클럽 게시판 삭제")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long boardId,
            @AuthenticationPrincipal UserDetails userDetails) {
        boardService.delete(boardId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시판이 삭제되었습니다.", null));
    }
} 