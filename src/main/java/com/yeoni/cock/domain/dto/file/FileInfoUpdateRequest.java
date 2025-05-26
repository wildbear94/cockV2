package com.yeoni.cock.domain.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfoUpdateRequest {
    private String originalFilename;
    private String fileCategory;       // image, video, document
    private Integer uploadOrder;
    private String thumbnailPath;
    private Boolean isProcessed;
}
