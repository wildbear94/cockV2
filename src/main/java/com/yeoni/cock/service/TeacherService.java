package com.yeoni.cock.service;

import com.yeoni.cock.domain.dto.teacher.TeacherCreateRequest;
import com.yeoni.cock.domain.dto.teacher.TeacherResponse;
import com.yeoni.cock.domain.dto.teacher.TeacherUpdateRequest;
import com.yeoni.cock.domain.entity.Teacher;
import com.yeoni.cock.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherMapper teacherMapper;

    @Transactional(readOnly = true)
    public List<TeacherResponse> findAll() {
        return teacherMapper.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TeacherResponse findById(Long teacherId, String userId) {
        Teacher teacher = teacherMapper.findById(teacherId, userId)
                .orElseThrow(() -> new RuntimeException("레슨 정보를 찾을 수 없습니다."));
        return convertToResponse(teacher);
    }

    @Transactional
    public void create(TeacherCreateRequest request, String userId) {
        Teacher teacher = new Teacher();
        teacher.setUserId(userId);
        teacher.setMainPhoto(request.getMainPhoto());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setLessonAddrDoro(request.getLessonAddrDoro());
        teacher.setLessonAddrJibun(request.getLessonAddrJibun());
        teacher.setLessonAddrEtc(request.getLessonAddrEtc());
        teacher.setSubPhoto(request.getSubPhoto());
        teacher.setCareerText(request.getCareerText());
        teacher.setLocalParkingYn(request.getLocalParkingYn());
        teacher.setLocalBathroomYn(request.getLocalBathroomYn());
        teacher.setLocalShowerYn(request.getLocalShowerYn());
        teacher.setLocalColdHeatYn(request.getLocalColdHeatYn());
        teacher.setLocalDressYn(request.getLocalDressYn());
        teacher.setPrYn("n"); // 기본값은 홍보 미승인
        teacher.setDelYn("n"); // 기본값은 삭제되지 않음
        teacher.setRegDt(LocalDateTime.now()); // 등록일 자동 설정
        teacher.setEntDt(request.getEntDt());

        teacherMapper.save(teacher);
    }

    @Transactional
    public void update(Long teacherId, TeacherUpdateRequest request, String userId) {
        Teacher teacher = teacherMapper.findById(teacherId, userId)
                .orElseThrow(() -> new RuntimeException("레슨 정보를 찾을 수 없습니다."));

        teacher.setMainPhoto(request.getMainPhoto());
        teacher.setPhoneNumber(request.getPhoneNumber());
        teacher.setLessonAddrDoro(request.getLessonAddrDoro());
        teacher.setLessonAddrJibun(request.getLessonAddrJibun());
        teacher.setLessonAddrEtc(request.getLessonAddrEtc());
        teacher.setSubPhoto(request.getSubPhoto());
        teacher.setCareerText(request.getCareerText());
        teacher.setLocalParkingYn(request.getLocalParkingYn());
        teacher.setLocalBathroomYn(request.getLocalBathroomYn());
        teacher.setLocalShowerYn(request.getLocalShowerYn());
        teacher.setLocalColdHeatYn(request.getLocalColdHeatYn());
        teacher.setLocalDressYn(request.getLocalDressYn());
        teacher.setPrYn(request.getPrYn());
        teacher.setEntDt(request.getEntDt());
        teacher.setUpdDt(LocalDateTime.now()); // 수정일 자동 설정

        teacherMapper.update(teacher);
    }

    @Transactional
    public void delete(Long teacherId, String userId) {
        Teacher teacher = teacherMapper.findById(teacherId, userId)
                .orElseThrow(() -> new RuntimeException("레슨 정보를 찾을 수 없습니다."));
        teacherMapper.delete(teacherId, userId);
    }

    @Transactional
    public void updatePromotionStatus(Long teacherId, String userId, String prYn) {
        Teacher teacher = teacherMapper.findById(teacherId, userId)
                .orElseThrow(() -> new RuntimeException("레슨 정보를 찾을 수 없습니다."));
        
        teacher.setPrYn(prYn);
        teacher.setUpdDt(LocalDateTime.now());
        teacherMapper.update(teacher);
    }

    @Transactional(readOnly = true)
    public List<TeacherResponse> findPromotedTeachers() {
        return teacherMapper.findPromotedTeachers().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TeacherResponse> findExpiredTeachers() {
        LocalDate today = LocalDate.now();
        return teacherMapper.findExpiredTeachers(today).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TeacherResponse convertToResponse(Teacher teacher) {
        TeacherResponse response = new TeacherResponse();
        response.setTeacherId(teacher.getTeacherId());
        response.setUserId(teacher.getUserId());
        response.setMainPhoto(teacher.getMainPhoto());
        response.setPhoneNumber(teacher.getPhoneNumber());
        response.setLessonAddrDoro(teacher.getLessonAddrDoro());
        response.setLessonAddrJibun(teacher.getLessonAddrJibun());
        response.setLessonAddrEtc(teacher.getLessonAddrEtc());
        response.setSubPhoto(teacher.getSubPhoto());
        response.setCareerText(teacher.getCareerText());
        response.setLocalParkingYn(teacher.getLocalParkingYn());
        response.setLocalBathroomYn(teacher.getLocalBathroomYn());
        response.setLocalShowerYn(teacher.getLocalShowerYn());
        response.setLocalColdHeatYn(teacher.getLocalColdHeatYn());
        response.setLocalDressYn(teacher.getLocalDressYn());
        response.setPrYn(teacher.getPrYn());
        response.setDelYn(teacher.getDelYn());
        response.setRegDt(teacher.getRegDt());
        response.setUpdDt(teacher.getUpdDt());
        response.setEntDt(teacher.getEntDt());
        return response;
    }
} 