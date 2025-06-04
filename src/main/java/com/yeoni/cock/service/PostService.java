package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.post.PostCreateRequest;
import com.yeoni.cock.domain.dto.post.PostResponse;
import com.yeoni.cock.domain.dto.post.PostUpdateRequest;
import com.yeoni.cock.domain.entity.Post;
import com.yeoni.cock.mapper.BoardMapper;
import com.yeoni.cock.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final BoardMapper boardMapper;
    private final CommentService commentService;

    @Transactional(readOnly = true)
    public List<PostResponse> findByBoardId(Long boardId, int page, int size, String keyword) {
        int offset = (page - 1) * size;
        List<Post> posts = postMapper.findByBoardId(boardId, offset, size, keyword);
        return posts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long postId) {
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        postMapper.incrementViewCount(postId);
        return convertToResponse(post);
    }

    @Transactional
    public void create(PostCreateRequest request, String userId) {
        // 게시판 존재 여부 확인
        boardMapper.findById(request.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        Post post = new Post();
        post.setBoardId(request.getBoardId());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setAuthorId(userId);
        post.setSecret(request.isSecret());
        post.setNotice(request.isNotice());
        post.setStatus("ACTIVE");
        post.setCreatedBy(userId);

        postMapper.save(post);
    }

    @Transactional
    public void update(Long postId, PostUpdateRequest request, String userId) {
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 작성자 확인
        if (!post.getAuthorId().equals(userId)) {
            throw new RuntimeException("게시글 수정 권한이 없습니다.");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setSecret(request.isSecret());
        post.setNotice(request.isNotice());
        post.setUpdatedBy(Long.parseLong(userId));

        postMapper.update(post);
    }

    @Transactional
    public void delete(Long postId, String userId) {
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 작성자 확인
        if (!post.getAuthorId().equals(userId)) {
            throw new RuntimeException("게시글 삭제 권한이 없습니다.");
        }

        postMapper.delete(postId);
    }

    private PostResponse convertToResponse(Post post) {
        PostResponse response = new PostResponse();
        response.setPostId(post.getPostId());
        response.setBoardId(post.getBoardId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setAuthorId(post.getAuthorId());
        response.setViewCount(post.getViewCount());
        response.setLikeCount(post.getLikeCount());
        response.setSecret(post.isSecret());
        response.setNotice(post.isNotice());
        response.setStatus(post.getStatus());
        response.setCreatedBy(post.getCreatedBy());
        response.setCreatedAt(post.getCreatedAt());
        response.setUpdatedBy(post.getUpdatedBy());
        response.setUpdatedAt(post.getUpdatedAt());
        
        // 댓글 목록 조회
        response.setComments(commentService.findByPostId(post.getPostId()));
        
        return response;
    }
} 