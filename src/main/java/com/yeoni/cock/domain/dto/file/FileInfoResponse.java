package com.yeoni.cock.domain.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoResponse {
    private Long fileId;
    private String originalFilename;
    private String downloadUri;
}
