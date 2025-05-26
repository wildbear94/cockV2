package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.club.ClubMemberResponse;
import com.yeoni.cock.domain.entity.ClubMember;
import com.yeoni.cock.mapper.ClubMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubMemberService {

    private final ClubMemberMapper clubMemberMapper;
    private static final int MAX_CLUBS_PER_USER = 4;

    @Transactional(readOnly = true)
    public List<ClubMemberResponse> findByUserId(String userId) {
        return clubMemberMapper.findByUserId(userId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ClubMemberResponse> findByClubId(Long clubId) {
        return clubMemberMapper.findByClubId(clubId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void join(Long clubId, String userId) {
        // 이미 가입된 클럽인지 확인
        if (clubMemberMapper.findByClubIdAndUserId(clubId, userId).isPresent()) {
            throw new RuntimeException("이미 가입된 클럽입니다.");
        }

        // 최대 가입 가능 클럽 수 확인
        if (clubMemberMapper.countByUserId(userId) >= MAX_CLUBS_PER_USER) {
            throw new RuntimeException("최대 " + MAX_CLUBS_PER_USER + "개의 클럽에만 가입할 수 있습니다.");
        }

        ClubMember member = new ClubMember();
        member.setClubId(clubId);
        member.setUserId(userId);
        member.setRole("MEMBER");
        member.setStatus("ACTIVE");
        member.setCreatedBy(userId);

        clubMemberMapper.save(member);
    }

    @Transactional
    public void leave(Long clubId, String userId) {
        ClubMember member = clubMemberMapper.findByClubIdAndUserId(clubId, userId)
                .orElseThrow(() -> new RuntimeException("클럽 회원을 찾을 수 없습니다."));

        member.setStatus("INACTIVE");
        member.setUpdatedBy(userId);

        clubMemberMapper.update(member);
    }

    private ClubMemberResponse convertToResponse(ClubMember member) {
        ClubMemberResponse response = new ClubMemberResponse();
        response.setMemberId(member.getMemberId());
        response.setClubId(member.getClubId());
        response.setUserId(member.getUserId());
        response.setRole(member.getRole());
        response.setStatus(member.getStatus());
        response.setJoinedAt(member.getJoinedAt());
        response.setLeftAt(member.getLeftAt());
        response.setCreatedBy(member.getCreatedBy());
        response.setCreatedAt(member.getCreatedAt());
        response.setUpdatedBy(member.getUpdatedBy());
        response.setUpdatedAt(member.getUpdatedAt());
        return response;
    }
} 