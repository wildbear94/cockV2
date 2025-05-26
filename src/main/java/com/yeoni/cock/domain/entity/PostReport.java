package com.yeoni.cock.domain.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostReport {
    private Long reportId;
    private Long postId;
    private String reporterId;
    private String reason;
    private LocalDateTime reportedAt;
}
