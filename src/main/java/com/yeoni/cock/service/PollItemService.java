package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.poll.PollItemCreateRequest;
import com.yeoni.cock.domain.dto.poll.PollItemResponse;
import com.yeoni.cock.domain.dto.poll.PollItemUpdateRequest;
import com.yeoni.cock.domain.entity.PollItem;
import com.yeoni.cock.mapper.PollItemMapper;
import com.yeoni.cock.mapper.PollMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollItemService {

    private final PollItemMapper pollItemMapper;
    private final PollMapper pollMapper;

    @Transactional(readOnly = true)
    public List<PollItemResponse> findByPollId(Long pollId) {
        return pollItemMapper.findByPollId(pollId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PollItemResponse findById(Long itemId) {
        PollItem item = pollItemMapper.findById(itemId)
                .orElseThrow(() -> new RuntimeException("투표 항목을 찾을 수 없습니다."));
        return convertToResponse(item);
    }

    @Transactional
    public void create(PollItemCreateRequest request) {
        // 투표 존재 여부 확인
        pollMapper.findById(request.getPollId())
                .orElseThrow(() -> new RuntimeException("투표를 찾을 수 없습니다."));

        PollItem item = new PollItem();
        item.setPollId(request.getPollId());
        item.setItemDescription(request.getItemDescription());
        item.setItemOrder(request.getItemOrder());

        pollItemMapper.save(item);
    }

    @Transactional
    public void update(Long itemId, PollItemUpdateRequest request) {
        PollItem item = pollItemMapper.findById(itemId)
                .orElseThrow(() -> new RuntimeException("투표 항목을 찾을 수 없습니다."));

        item.setItemDescription(request.getItemDescription());
        item.setItemOrder(request.getItemOrder());

        pollItemMapper.update(item);
    }

    @Transactional
    public void delete(Long itemId) {
        pollItemMapper.delete(itemId);
    }

    private PollItemResponse convertToResponse(PollItem item) {
        PollItemResponse response = new PollItemResponse();
        response.setItemId(item.getItemId());
        response.setPollId(item.getPollId());
        response.setItemDescription(item.getItemDescription());
        response.setItemOrder(item.getItemOrder());
        response.setCreatedAt(item.getCreatedAt());
        response.setUpdatedAt(item.getUpdatedAt());
        return response;
    }
} 