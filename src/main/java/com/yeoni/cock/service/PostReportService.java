package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.post.PostReportRequest;
import com.yeoni.cock.domain.entity.PostReport;
import com.yeoni.cock.mapper.PostReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReportService {

    private final PostReportMapper reportMapper;

    public void reportPost(PostReportRequest dto) {
        if (reportMapper.existsByPostIdAndReporterId(dto.getPostId(), dto.getReporterId())) {
            throw new IllegalStateException("이미 신고한 게시글입니다.");
        }
        reportMapper.insertReport(dto);
    }

    public List<PostReport> getAllReports() {
        return reportMapper.selectAllReports();
    }

    public PostReport getReport(Long id) {
        return reportMapper.selectReportById(id);
    }

    public void updateReport(Long id, String reason) {
        reportMapper.updateReportReason(id, reason);
    }

    public void deleteReport(Long id) {
        reportMapper.deleteReport(id);
    }
}
