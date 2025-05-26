package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.comment.CommentCreateRequest;
import com.yeoni.cock.domain.dto.comment.CommentResponse;
import com.yeoni.cock.domain.dto.comment.CommentUpdateRequest;
import com.yeoni.cock.domain.entity.Comment;
import com.yeoni.cock.mapper.CommentMapper;
import com.yeoni.cock.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final PostMapper postMapper;

    @Transactional(readOnly = true)
    public List<CommentResponse> findByPostId(Long postId) {
        List<Comment> comments = commentMapper.findByPostId(postId);
        return buildCommentTree(comments);
    }

    @Transactional
    public void create(CommentCreateRequest request, String userId) {
        // 게시글 존재 여부 확인
        postMapper.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 부모 댓글 존재 여부 확인
        if (request.getParentId() != null) {
            commentMapper.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("부모 댓글을 찾을 수 없습니다."));
        }

        Comment comment = new Comment();
        comment.setPostId(request.getPostId());
        comment.setParentId(request.getParentId());
        comment.setClubId(request.getClubId());
        comment.setContent(request.getContent());
        comment.setAuthorId(userId);
        comment.setSecret(request.isSecret());
        comment.setStatus("ACTIVE");

        commentMapper.save(comment);
    }

    @Transactional
    public void update(Long commentId, CommentUpdateRequest request, String userId) {
        Comment comment = commentMapper.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        // 작성자 확인
        if (!comment.getAuthorId().equals(userId)) {
            throw new RuntimeException("댓글 수정 권한이 없습니다.");
        }

        comment.setContent(request.getContent());
        comment.setSecret(request.isSecret());

        commentMapper.update(comment);
    }

    @Transactional
    public void delete(Long commentId, String userId) {
        Comment comment = commentMapper.findById(commentId)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));

        // 작성자 확인
        if (!comment.getAuthorId().equals(userId)) {
            throw new RuntimeException("댓글 삭제 권한이 없습니다.");
        }

        commentMapper.delete(commentId);
    }

    private List<CommentResponse> buildCommentTree(List<Comment> comments) {
        Map<Long, List<Comment>> parentMap = comments.stream()
                .collect(Collectors.groupingBy(comment -> 
                    comment.getParentId() != null ? comment.getParentId() : 0L));

        return parentMap.getOrDefault(0L, new ArrayList<>()).stream()
                .map(comment -> convertToResponse(comment, parentMap))
                .collect(Collectors.toList());
    }

    private CommentResponse convertToResponse(Comment comment, Map<Long, List<Comment>> parentMap) {
        CommentResponse response = new CommentResponse();
        response.setCommentId(comment.getCommentId());
        response.setPostId(comment.getPostId());
        response.setParentId(comment.getParentId());
        response.setClubId(comment.getClubId());
        response.setContent(comment.getContent());
        response.setAuthorId(comment.getAuthorId());
        response.setLikeCount(comment.getLikeCount());
        response.setSecret(comment.isSecret());
        response.setStatus(comment.getStatus());
        response.setCreatedAt(comment.getCreatedAt());

        List<Comment> replies = parentMap.getOrDefault(comment.getCommentId(), new ArrayList<>());
        response.setReplies(replies.stream()
                .map(reply -> convertToResponse(reply, parentMap))
                .collect(Collectors.toList()));

        return response;
    }
} 