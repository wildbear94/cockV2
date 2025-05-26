package com.yeoni.cock.mapper;

import com.yeoni.cock.domain.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface TeacherMapper {
    List<Teacher> findAll();
    Optional<Teacher> findById(@Param("teacherId") Long teacherId, @Param("userId") String userId);
    void save(@Param("teacher") Teacher teacher);
    void update(@Param("teacher") Teacher teacher);
    void delete(@Param("teacherId") Long teacherId, @Param("userId") String userId);
    List<Teacher> findPromotedTeachers();
    List<Teacher> findExpiredTeachers(@Param("today") LocalDate today);
} 