package com.yeoni.cock.controller.comment;

import com.yeoni.cock.domain.dto.comment.CommentCreateRequest;
import com.yeoni.cock.domain.dto.comment.CommentResponse;
import com.yeoni.cock.domain.dto.comment.CommentUpdateRequest;
import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.service.CommentService;
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
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@Tag(name = "댓글 API", description = "댓글 관련 API")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    @Operation(summary = "댓글 작성", description = "게시글에 새로운 댓글을 작성합니다.")
    public ResponseEntity<ApiResponse<Void>> createComment(
        @PathVariable Long postId,
        @Valid @RequestBody CommentCreateRequest request,
        @AuthenticationPrincipal UserDetails userDetails) {
        commentService.create(request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("게시글에 새로운 댓글을 작성 되었습니다.", null));

    }

    @GetMapping("/{postId}")
    @Operation(summary = "댓글 목록 조회", description = "게시글의 댓글 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(ApiResponse.success(commentService.findByPostId(postId)));
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "댓글 수정", description = "작성한 댓글을 수정합니다.")
    public ResponseEntity<ApiResponse<Void>> updateComment(
        @PathVariable Long commentId,
        @Valid @RequestBody CommentUpdateRequest request,
        @AuthenticationPrincipal UserDetails userDetails) {
        commentService.update(commentId, request, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("댓글이 수정되었습니다.", null));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "댓글 삭제", description = "작성한 댓글을 삭제합니다.")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetails userDetails) {
        commentService.delete(commentId, userDetails.getUsername());
        return ResponseEntity.ok(ApiResponse.success("댓글이 삭제되었습니다.", null));
    }

    /*
    @PostMapping("/{commentId}/like")
    @Operation(summary = "댓글 좋아요", description = "댓글에 좋아요를 추가합니다.")
    public ResponseEntity<Void> likeComment(
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetails userDetails) {
        commentService.likeComment(commentId, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}/like")
    @Operation(summary = "댓글 좋아요 취소", description = "댓글의 좋아요를 취소합니다.")
    public ResponseEntity<Void> unlikeComment(
        @PathVariable Long commentId,
        @AuthenticationPrincipal UserDetails userDetails) {
        commentService.unlikeComment(commentId, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }
    */
}
