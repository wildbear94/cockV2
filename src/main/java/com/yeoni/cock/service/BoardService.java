package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.board.BoardCreateRequest;
import com.yeoni.cock.domain.dto.board.BoardResponse;
import com.yeoni.cock.domain.dto.board.BoardUpdateRequest;
import com.yeoni.cock.domain.entity.Board;
import com.yeoni.cock.mapper.BoardMapper;
import com.yeoni.cock.mapper.ClubMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final ClubMemberMapper clubMemberMapper;

    @Transactional(readOnly = true)
    public List<BoardResponse> findByClubId(Long clubId) {
        return boardMapper.findByClubId(clubId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardResponse findById(Long boardId) {
        Board board = boardMapper.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));
        return convertToResponse(board);
    }

    @Transactional
    public void create(BoardCreateRequest request, String userId) {
        // 클럽 회원 권한 확인
        clubMemberMapper.findByClubIdAndUserId(request.getClubId(), userId)
                .orElseThrow(() -> new RuntimeException("클럽 회원이 아닙니다."));

        Board board = new Board();
        board.setClubId(request.getClubId());
        board.setName(request.getName());
        board.setDescription(request.getDescription());
        board.setType(request.getType());
        board.setStatus("ACTIVE");

        boardMapper.save(board);
    }

    @Transactional
    public void update(Long boardId, BoardUpdateRequest request, String userId) {
        Board board = boardMapper.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        // 클럽 회원 권한 확인
        clubMemberMapper.findByClubIdAndUserId(board.getClubId(), userId)
                .orElseThrow(() -> new RuntimeException("클럽 회원이 아닙니다."));

        board.setName(request.getName());
        board.setDescription(request.getDescription());
        board.setType(request.getType());

        boardMapper.update(board);
    }

    @Transactional
    public void delete(Long boardId, String userId) {
        Board board = boardMapper.findById(boardId)
                .orElseThrow(() -> new RuntimeException("게시판을 찾을 수 없습니다."));

        // 클럽 회원 권한 확인
        clubMemberMapper.findByClubIdAndUserId(board.getClubId(), userId)
                .orElseThrow(() -> new RuntimeException("클럽 회원이 아닙니다."));

        boardMapper.delete(boardId);
    }

    private BoardResponse convertToResponse(Board board) {
        BoardResponse response = new BoardResponse();
        response.setBoardId(board.getBoardId());
        response.setClubId(board.getClubId());
        response.setName(board.getName());
        response.setDescription(board.getDescription());
        response.setStatus(board.getStatus());
        response.setType(board.getType());
        return response;
    }
} 