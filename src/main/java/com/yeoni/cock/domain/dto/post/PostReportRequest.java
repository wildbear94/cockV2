package com.yeoni.cock.domain.dto.post;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostReportRequest {
    private Long reportId;
    private Long postId;
    private String reporterId;
    private String reason;
    private LocalDateTime reportedAt;
}
