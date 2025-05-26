package com.yeoni.cock.domain.dto.club;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClubResponse {
    private Long clubId;
    private String name;
    private String description;
    private String address;
    private String addressDetail;
    private Boolean isActive;
    private String introduction;
    private String inquiryContact;
    private String inquiryPhone;
    private Boolean isPromoted;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private Boolean isDeleted;
    private Integer currentMembers;
    private Boolean isPublic;
    private Integer maxMembers;
} 