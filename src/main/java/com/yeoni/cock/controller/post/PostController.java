package com.yeoni.cock.controller.post;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.post.PostCreateRequest;
import com.yeoni.cock.domain.dto.post.PostResponse;
import com.yeoni.cock.domain.dto.post.PostUpdateRequest;
import com.yeoni.cock.service.PostService;
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
@RequestMapping("/api/boards/{boardId}/posts")
@RequiredArgsConstructor
@Tag(name = "게시글 API", description = "게시글 관련 API")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 목록", description = "게시글 목록")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostResponse>>> findByBoardId(@PathVariable Long boardId) {
        return ResponseEntity.ok(ApiResponse.success(postService.findByBoardId(boardId)));
    }

    @Operation(summary = "게시글 상세", description = "게시글 상세")
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponse>> findById(@PathVariable Long postId) {
        return ResponseEntity.ok(ApiResponse.success(postService.findById(postId)));
    }

    @Operation(summary = "게시글 생성", description = "게시글 생성")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> create(
            @PathVariable Long boardId,
            @Valid @RequestBody PostCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        request.setBoardId(boardId);
        postService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시글이 등록되었습니다.", null));
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> update(
            @PathVariable Long postId,
            @Valid @RequestBody PostUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        postService.update(postId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시글이 수정되었습니다.", null));
    }

    @Operation(summary = "게시글 삭제", description = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetails userDetails) {
        postService.delete(postId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시글이 삭제되었습니다.", null));
    }
} 