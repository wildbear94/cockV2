package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.poll.PollCreateRequest;
import com.yeoni.cock.domain.dto.poll.PollResponse;
import com.yeoni.cock.domain.dto.poll.PollUpdateRequest;
import com.yeoni.cock.domain.entity.Poll;
import com.yeoni.cock.mapper.ClubMapper;
import com.yeoni.cock.mapper.PollMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollMapper pollMapper;
    private final ClubMapper clubMapper;

    @Transactional(readOnly = true)
    public List<PollResponse> findByClubId(Long clubId) {
        return pollMapper.findByClubId(clubId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PollResponse findById(Long pollId) {
        Poll poll = pollMapper.findById(pollId)
                .orElseThrow(() -> new RuntimeException("투표를 찾을 수 없습니다."));
        return convertToResponse(poll);
    }

    @Transactional
    public void create(PollCreateRequest request, String userId) {
        // 클럽 존재 여부 확인
        clubMapper.findById(request.getClubId())
                .orElseThrow(() -> new RuntimeException("클럽을 찾을 수 없습니다."));

        Poll poll = new Poll();
        poll.setClubId(request.getClubId());
        poll.setPollName(request.getPollName());
        poll.setPollStartDate(request.getPollStartDate());
        poll.setPollEndDate(request.getPollEndDate());
        poll.setPollType(request.getPollType());
        poll.setPollDisposalFlag("ACTIVE");
        poll.setPollAutoDisposal(request.isPollAutoDisposal());
        poll.setCreatedBy(userId);

        pollMapper.save(poll);
    }

    @Transactional
    public void update(Long pollId, PollUpdateRequest request, String userId) {
        Poll poll = pollMapper.findById(pollId)
                .orElseThrow(() -> new RuntimeException("투표를 찾을 수 없습니다."));

        poll.setPollName(request.getPollName());
        poll.setPollStartDate(request.getPollStartDate());
        poll.setPollEndDate(request.getPollEndDate());
        poll.setPollType(request.getPollType());
        poll.setPollAutoDisposal(request.isPollAutoDisposal());
        poll.setUpdatedBy(userId);

        pollMapper.update(poll);
    }

    @Transactional
    public void delete(Long pollId) {
        pollMapper.delete(pollId);
    }

    private PollResponse convertToResponse(Poll poll) {
        PollResponse response = new PollResponse();
        response.setPollId(poll.getPollId());
        response.setClubId(poll.getClubId());
        response.setPollName(poll.getPollName());
        response.setPollStartDate(poll.getPollStartDate());
        response.setPollEndDate(poll.getPollEndDate());
        response.setPollType(poll.getPollType());
        response.setPollDisposalFlag(poll.getPollDisposalFlag());
        response.setPollAutoDisposal(poll.isPollAutoDisposal());
        response.setCreatedBy(poll.getCreatedBy());
        response.setCreatedAt(poll.getCreatedAt());
        response.setUpdatedBy(poll.getUpdatedBy());
        response.setUpdatedAt(poll.getUpdatedAt());
        return response;
    }
} 