package com.yeoni.cock.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Club {
    private Long clubId;
    private String name;
    private String description;
    private String address;
    private String addressDetail;
    private boolean isActive;
    private String introduction;
    private String inquiryContact;
    private String inquiryPhone;
    private boolean isPromoted;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
    private int currentMembers;
    private boolean isPublic;
    private int maxMembers;
} 