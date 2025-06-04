package com.yeoni.cock.controller.file;

import com.yeoni.cock.domain.dto.common.ApiResponse;
import com.yeoni.cock.domain.dto.file.FileInfoUpdateRequest;
import com.yeoni.cock.domain.dto.file.FileInfoResponse;
import com.yeoni.cock.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "파일 API", description = "파일 관련 API")
public class FileController {

    private final FileService fileService;

    @Operation(summary = "다중 파일 업로드", description = "다중 파일 업로드")
    @PostMapping("/upload")
    public ResponseEntity<ApiResponse<Void>> uploadMultiple(
        @RequestParam("files") List<MultipartFile> files,
        @RequestParam("ownerType") String ownerType,
        @RequestParam("ownerId") Long ownerId
    ) throws Exception {
        List<FileInfoResponse> respList = fileService.uploadFiles(files, ownerType, ownerId);
        return ResponseEntity.ok(ApiResponse.success("파일이 등록되었습니다.", null));
    }

    @Operation(summary = "파일 정보 수정", description = "파일 정보 수정")
    @PutMapping("/{fileId}")
    public ResponseEntity<ApiResponse<Void>> updateFile(
        @PathVariable Long fileId,
        @RequestBody FileInfoUpdateRequest req
    ) {
        fileService.updateFileInfo(fileId, req);
        return ResponseEntity.ok(ApiResponse.success("파일이 수정되었습니다.", null));
    }

    @Operation(summary = "파일 정보 삭제", description = "파일 정보 삭제")
    @DeleteMapping("/{fileId}")
    public ResponseEntity<ApiResponse<Void>> deleteFile(@PathVariable Long fileId) throws Exception {
        fileService.deleteFile(fileId);
        return ResponseEntity.ok(ApiResponse.success("파일이 삭제되었습니다.", null));
    }

    @Operation(summary = "파일 그룹 삭제", description = "파일 그룹 삭제")
    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<ApiResponse<Void>> deleteGroup(@PathVariable Long groupId) throws Exception {
        fileService.deleteFileGroup(groupId);
        return ResponseEntity.ok(ApiResponse.success("파일그룹이 삭제되었습니다.", null));
    }

    @Operation(summary = "파일 다운로드", description = "파일 다운로드")
    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(
        @PathVariable Long fileId,
        HttpServletRequest request
    ) throws Exception {
        Resource resource = fileService.downloadFile(fileId);

        // 1) 콘텐츠 타입 결정
        String contentType = null;
        try {
            contentType = Files.probeContentType(Paths.get(resource.getFile().getAbsolutePath()));
        } catch (IOException ignored) {}
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        // 2) 헤더 설정 후 스트리밍 응답
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }


}
