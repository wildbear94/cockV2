package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.dto.post.PostReportRequest;
import com.yeoni.cock.domain.entity.PostReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostReportMapper {

    void insertReport(PostReportRequest dto);
    List<PostReport> selectAllReports();
    PostReport selectReportById(Long reportId);
    void updateReportReason(@Param("reportId") Long reportId, @Param("reason") String reason);
    void deleteReport(Long reportId);
    boolean existsByPostIdAndReporterId(@Param("postId") Long postId, @Param("reporterId") String reporterId);
}
