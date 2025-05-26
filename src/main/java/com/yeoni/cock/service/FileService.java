package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.file.FileInfoResponse;
import com.yeoni.cock.domain.dto.file.FileInfoUpdateRequest;
import com.yeoni.cock.domain.entity.FileGroup;
import com.yeoni.cock.domain.entity.FileInfo;
import com.yeoni.cock.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileMapper fileMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Transactional
    public List<FileInfoResponse> uploadFiles(List<MultipartFile> files,
                                              String ownerType,
                                              Long ownerId) throws Exception {
        // 1) 파일 그룹 생성

        int totalFiles = files.size();
        if (totalFiles == 0) throw new IllegalArgumentException("No files to upload");

        FileGroup group = FileGroup.builder()
            .ownerType(ownerType)
            .ownerId(ownerId)
            .totalFiles(0)
            .build();
        fileMapper.insertFileGroup(group);

        List<FileInfoResponse> responses = new ArrayList<>();
        int order = 0;

        for (MultipartFile file : files) {
            // 2) 실제 파일 저장
            if(!Objects.requireNonNull(file.getOriginalFilename()).isEmpty() || file.getSize() != 0){

                // 확장자 추출 (com.google.common.io.Files
                String ext = com.google.common.io.Files.getFileExtension(file.getOriginalFilename());
                String storedName = UUID.randomUUID() + "." + ext;
                Path target = Paths.get(uploadDir).resolve(storedName);
                Files.createDirectories(target.getParent());
                Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

                // 3) DB 저장
                FileInfo info = FileInfo.builder()
                    .fileGroupId(group.getFileGroupId())
                    .originalFilename(file.getOriginalFilename())
                    .storedFilename(storedName)
                    .fileExtension(ext)
                    .fileCategory(determineCategory(ext))
                    .fileSize(file.getSize())
                    .storagePath(target.toString())
                    .uploadOrder(order++)
                    .isProcessed(false)
                    .createdAt(LocalDateTime.now())
                    .build();
                fileMapper.insertFile(info);

            }
            // 개별 응답 빌드
            /*
            String downloadUri = "/api/files/download/" + info.getFileId();
            responses.add(FileInfoResponse.builder()
                .fileId(info.getFileId())
                .originalFilename(info.getOriginalFilename())
                .downloadUri(downloadUri)
                .build());
            */
        }

        // 4) 그룹 totalFiles 업데이트
        fileMapper.updateFileGroupTotal(group.getFileGroupId(), files.size());

        return responses;
    }

    @Transactional
    public void updateFileInfo(Long fileId, FileInfoUpdateRequest req) {
        // 1) DB에 레코드 존재 확인 (선택사항)
        FileInfo existing = fileMapper.selectFileById(fileId);
        if (existing == null) {
            throw new NoSuchElementException("File not found: " + fileId);
        }
        // 2) 업데이트
        int updated = fileMapper.updateFileInfo(fileId, req);
        if (updated != 1) {
            throw new IllegalStateException("Failed to update file info for id " + fileId);
        }
    }

    @Transactional
    public void deleteFile(Long fileId) throws Exception {
        // 1) 파일 정보 조회
        FileInfo info = fileMapper.selectFileById(fileId);
        if (info == null) {
            throw new NoSuchElementException("File not found: " + fileId);
        }

        // 2) 파일 시스템에서 삭제
        try {
            Files.deleteIfExists(Paths.get(info.getStoragePath()));
            if (info.getThumbnailPath() != null) {
                Files.deleteIfExists(Paths.get(info.getThumbnailPath()));
            }
        } catch (IOException e) {
            // 로그 남기고 계속 진행할지, 예외로 롤백할지 결정
            throw new IOException("Failed to delete file from storage", e);
        }

        // 3) DB 레코드 삭제
        int deleted = fileMapper.deleteFileById(fileId);
        if (deleted != 1) {
            throw new IllegalStateException("Failed to delete DB record for file: " + fileId);
        }

        // 4) 그룹 totalFiles 감소
        fileMapper.updateFileGroupTotal(info.getFileGroupId(), -1);
    }

    @Transactional
    public void deleteFileGroup(Long groupId) throws Exception {
        // 1) 그룹 내 파일 메타 조회
        List<FileInfo> files = fileMapper.selectFilesByGroupId(groupId);

        // 2) 파일 시스템에서 모두 삭제
        for (FileInfo info : files) {
            try {
                Files.deleteIfExists(Paths.get(info.getStoragePath()));
                if (info.getThumbnailPath() != null) {
                    Files.deleteIfExists(Paths.get(info.getThumbnailPath()));
                }
            } catch (IOException e) {
                // 실패 시 롤백되도록 예외 던짐
                throw new IOException("Failed to delete file: " + info.getFileId(), e);
            }
        }

        // 3) DB에서 tb_file_group 삭제 (tb_file은 CASCADE로 자동 삭제)
        int deleted = fileMapper.deleteFileGroupById(groupId);
        if (deleted != 1) {
            throw new NoSuchElementException("FileGroup not found: " + groupId);
        }
    }

    public Resource downloadFile(Long fileId) throws Exception {
        // 1) DB에서 메타 정보 조회
        FileInfo info = fileMapper.selectFileById(fileId);
        if (info == null) {
            throw new NoSuchElementException("File not found: " + fileId);
        }

        // 2) 실제 파일 로드
        Path path = Paths.get(info.getStoragePath());
        Resource resource = new UrlResource(path.toUri());
        if (!resource.exists() || !resource.isReadable()) {
            throw new FileNotFoundException("Could not read file: " + info.getStoredFilename());
        }

        // 3) 다운로드 횟수 증가
        fileMapper.incrementDownloadCount(fileId);

        return resource;
    }

    private String determineCategory(String ext) {
        ext = ext.toLowerCase();
        if (ext.matches("png|jpg|jpeg|gif")) return "image";
        if (ext.matches("mp4|avi|mov")) return "video";
        return "document";
    }
}
