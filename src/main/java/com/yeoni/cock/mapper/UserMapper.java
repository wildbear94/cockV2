package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.dto.auth.LoginRequest;
import com.yeoni.cock.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findByUsername(@Param("username") String username);
    Optional<User> findByEmail(@Param("email") String email);
    Optional<User> findByUserId(@Param("userId") String userId);
    List<User> findAll(@Param("offset") int offset, @Param("limit") int limit);
    long countUserId(@Param("userId") String userId);
    void save(@Param("user") User user);
    void update(@Param("user") User user);
    void delete(@Param("userId") String userId);
    void updateAppId(@Param("loginRequest") LoginRequest loginRequest);

}