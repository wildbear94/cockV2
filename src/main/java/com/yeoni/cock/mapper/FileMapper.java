package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.dto.file.FileInfoUpdateRequest;
import com.yeoni.cock.domain.entity.FileGroup;
import com.yeoni.cock.domain.entity.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {
    int insertFileGroup(FileGroup fileGroup);
    int updateFileGroupTotal(@Param("groupId") Long groupId, @Param("delta") int delta);
    int insertFile(FileInfo fileInfo);
    FileInfo selectFileById(Long fileId);
    int incrementDownloadCount(Long fileId);
    int updateFileInfo(@Param("fileId") Long fileId, @Param("info") FileInfoUpdateRequest info);
    int deleteFileById(Long fileId);
    // 그룹 내 모든 파일 조회
    List<FileInfo> selectFilesByGroupId(Long groupId);
    // 그룹 삭제
    int deleteFileGroupById(Long groupId);

}
