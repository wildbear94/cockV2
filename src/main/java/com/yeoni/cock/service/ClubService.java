package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.club.ClubCreateRequest;
import com.yeoni.cock.domain.dto.club.ClubResponse;
import com.yeoni.cock.domain.dto.club.ClubUpdateRequest;
import com.yeoni.cock.domain.entity.Club;
import com.yeoni.cock.domain.entity.ClubMember;
import com.yeoni.cock.mapper.ClubMapper;
import com.yeoni.cock.mapper.ClubMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubMapper clubMapper;
    private final ClubMemberMapper clubMemberMapper;

    @Transactional(readOnly = true)
    public List<ClubResponse> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return clubMapper.findAll(offset, size).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long count() {
        return clubMapper.count();
    }

    @Transactional(readOnly = true)
    public ClubResponse findById(Long clubId) {
        Club club = clubMapper.findById(clubId)
                .orElseThrow(() -> new RuntimeException("클럽을 찾을 수 없습니다."));
        return convertToResponse(club);
    }

    @Transactional
    public void create(ClubCreateRequest request, String userId) {
        // 클럽 생성
        Club club = new Club();
        club.setName(request.getName());
        club.setDescription(request.getDescription());
        club.setAddress(request.getAddress());
        club.setAddressDetail(request.getAddressDetail());
        club.setActive(request.isActive());
        club.setIntroduction(request.getIntroduction());
        club.setInquiryContact(request.getInquiryContact());
        club.setInquiryPhone(request.getInquiryPhone());
        club.setPromoted(request.isPromoted());
        club.setPublic(request.isPublic());
        club.setMaxMembers(request.getMaxMembers());
        club.setCreatedBy(userId);

        clubMapper.save(club);

        // 클럽 생성자를 OWNER로 등록
        ClubMember member = new ClubMember();
        member.setClubId(club.getClubId());
        member.setUserId(userId);
        member.setRole("OWNER");
        member.setStatus("ACTIVE");
        member.setCreatedBy(userId);

        clubMemberMapper.save(member);
    }

    @Transactional
    public void update(Long clubId, ClubUpdateRequest request, String userId) {
        Club club = clubMapper.findById(clubId)
                .orElseThrow(() -> new RuntimeException("클럽을 찾을 수 없습니다."));

        club.setName(request.getName());
        club.setDescription(request.getDescription());
        club.setAddress(request.getAddress());
        club.setAddressDetail(request.getAddressDetail());
        club.setActive(request.isActive());
        club.setIntroduction(request.getIntroduction());
        club.setInquiryContact(request.getInquiryContact());
        club.setInquiryPhone(request.getInquiryPhone());
        club.setPromoted(request.isPromoted());
        club.setPublic(request.isPublic());
        club.setMaxMembers(request.getMaxMembers());
        club.setUpdatedBy(userId);

        clubMapper.update(club);
    }

    @Transactional
    public void delete(Long clubId) {
        if (!clubMapper.findById(clubId).isPresent()) {
            throw new RuntimeException("클럽을 찾을 수 없습니다.");
        }
        clubMapper.delete(clubId);
    }

    private ClubResponse convertToResponse(Club club) {
        ClubResponse response = new ClubResponse();
        response.setClubId(club.getClubId());
        response.setName(club.getName());
        response.setDescription(club.getDescription());
        response.setAddress(club.getAddress());
        response.setAddressDetail(club.getAddressDetail());
        response.setIsActive(club.isActive());
        response.setIntroduction(club.getIntroduction());
        response.setInquiryContact(club.getInquiryContact());
        response.setInquiryPhone(club.getInquiryPhone());
        response.setIsPromoted(club.isPromoted());
        response.setCreatedBy(club.getCreatedBy());
        response.setCreatedAt(club.getCreatedAt());
        response.setUpdatedBy(club.getUpdatedBy());
        response.setUpdatedAt(club.getUpdatedAt());
        response.setCurrentMembers(club.getCurrentMembers());
        response.setIsPublic(club.isPublic());
        response.setMaxMembers(club.getMaxMembers());
        return response;
    }
} 