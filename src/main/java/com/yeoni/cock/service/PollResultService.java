package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.poll.PollResultCreateRequest;
import com.yeoni.cock.domain.dto.poll.PollResultResponse;
import com.yeoni.cock.domain.entity.Poll;
import com.yeoni.cock.domain.entity.PollItem;
import com.yeoni.cock.domain.entity.PollResult;
import com.yeoni.cock.mapper.PollItemMapper;
import com.yeoni.cock.mapper.PollMapper;
import com.yeoni.cock.mapper.PollResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollResultService {

    private final PollResultMapper pollResultMapper;
    private final PollMapper pollMapper;
    private final PollItemMapper pollItemMapper;

    @Transactional(readOnly = true)
    public List<PollResultResponse> findByPollId(Long pollId) {
        return pollResultMapper.findByPollId(pollId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PollResultResponse> findByUserId(Long pollId, String userId) {
        return pollResultMapper.findByUserId(pollId, userId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PollResultResponse findById(Long resultId) {
        PollResult result = pollResultMapper.findById(resultId)
                .orElseThrow(() -> new RuntimeException("투표 결과를 찾을 수 없습니다."));
        return convertToResponse(result);
    }

    @Transactional
    public void create(PollResultCreateRequest request, String userId) {
        // 투표 존재 여부 확인
        Poll poll = pollMapper.findById(request.getPollId())
                .orElseThrow(() -> new RuntimeException("투표를 찾을 수 없습니다."));

        // 투표 기간 확인
        LocalDate today = LocalDate.now();
        if (today.isBefore(poll.getPollStartDate()) || today.isAfter(poll.getPollEndDate())) {
            throw new RuntimeException("투표 기간이 아닙니다.");
        }

        // 투표 항목 존재 여부 확인
        PollItem item = pollItemMapper.findById(request.getItemId())
                .orElseThrow(() -> new RuntimeException("투표 항목을 찾을 수 없습니다."));

        // 투표 유형에 따른 중복 투표 체크
        if ("SINGLE".equals(poll.getPollType())) {
            // 기존 투표 결과 삭제
            pollResultMapper.deleteByPollIdAndUserId(request.getPollId(), userId);
        }

        PollResult result = new PollResult();
        result.setPollId(request.getPollId());
        result.setItemId(request.getItemId());
        result.setUserId(userId);

        pollResultMapper.save(result);
    }

    @Transactional
    public void delete(Long resultId, String userId) {
        PollResult result = pollResultMapper.findById(resultId)
                .orElseThrow(() -> new RuntimeException("투표 결과를 찾을 수 없습니다."));

        // 본인의 투표만 삭제 가능
        if (!result.getUserId().equals(userId)) {
            throw new RuntimeException("본인의 투표만 삭제할 수 있습니다.");
        }

        pollResultMapper.delete(resultId);
    }

    @Transactional(readOnly = true)
    public Map<Long, Integer> getPollStatistics(Long pollId) {
        List<PollItem> items = pollItemMapper.findByPollId(pollId);
        return items.stream()
                .collect(Collectors.toMap(
                        PollItem::getItemId,
                        item -> pollResultMapper.countByPollIdAndItemId(pollId, item.getItemId())
                ));
    }

    private PollResultResponse convertToResponse(PollResult result) {
        PollResultResponse response = new PollResultResponse();
        response.setResultId(result.getResultId());
        response.setPollId(result.getPollId());
        response.setItemId(result.getItemId());
        response.setUserId(result.getUserId());
        response.setVotedAt(result.getVotedAt());
        return response;
    }
} 