package com.yeoni.cock.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileGroup {
    private Long fileGroupId;
    private String ownerType;
    private Long ownerId;
    private Integer totalFiles;
    private LocalDateTime createdAt;
}
