package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.auth.LoginRequest;
import com.yeoni.cock.domain.dto.user.UserDetailResponse;
import com.yeoni.cock.domain.dto.user.UserListResponse;
import com.yeoni.cock.domain.dto.user.UserUpdateRequest;
import com.yeoni.cock.domain.entity.User;
import com.yeoni.cock.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public List<UserListResponse> findAll(int page, int size) {
        int offset = (page - 1) * size;
        return userMapper.findAll(offset, size).stream()
                .map(this::convertToUserListResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long count(LoginRequest request) {
        return userMapper.countUserId(request.getUserId());
    }

    @Transactional(readOnly = true)
    public UserDetailResponse findById(String userId) {
        User user = userMapper.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        return convertToUserDetailResponse(user);
    }

    @Transactional
    public void update(String userId, UserUpdateRequest request) {
        User user = userMapper.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        // 이메일 중복 체크 (다른 사용자가 사용 중인지)
        if (!user.getEmail().equals(request.getEmail()) &&
            userMapper.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }

        user.setEmail(request.getEmail());
        user.setNickname(request.getNickname());
        user.setBirthDate(request.getBirthDate());
        user.setLunar(request.isLunar());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPostalCode(request.getPostalCode());
        user.setAddress(request.getAddress());
        user.setAddressDetail(request.getAddressDetail());

        userMapper.update(user);
    }

    @Transactional
    public void delete(String userId) {
        if (!userMapper.findByUserId(userId).isPresent()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        userMapper.delete(userId);
    }

    private UserListResponse convertToUserListResponse(User user) {
        UserListResponse response = new UserListResponse();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setNickname(user.getNickname());
        response.setStatus(user.getStatus());
        response.setRole(user.getRole());
        response.setBirthDate(user.getBirthDate());
        response.setLunar(user.isLunar());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    private UserDetailResponse convertToUserDetailResponse(User user) {
        UserDetailResponse response = new UserDetailResponse();
        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setNickname(user.getNickname());
        response.setStatus(user.getStatus());
        response.setRole(user.getRole());
        response.setBirthDate(user.getBirthDate());
        response.setLunar(user.isLunar());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setPostalCode(user.getPostalCode());
        response.setAddress(user.getAddress());
        response.setAddressDetail(user.getAddressDetail());
        response.setCreatedAt(user.getCreatedAt());
        response.setWithdrawalDatetime(user.getWithdrawalDatetime());
        response.setMembershipEndDate(user.getMembershipEndDate());
        return response;
    }
} 