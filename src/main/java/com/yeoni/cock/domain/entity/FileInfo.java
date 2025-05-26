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
public class FileInfo {
    private Long fileId;
    private Long fileGroupId;
    private String originalFilename;
    private String storedFilename;
    private String fileExtension;
    private String fileCategory;
    private Long fileSize;
    private String storagePath;
    private String thumbnailPath;
    private Integer uploadOrder;
    private Integer downloadCount;
    private Boolean isProcessed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
