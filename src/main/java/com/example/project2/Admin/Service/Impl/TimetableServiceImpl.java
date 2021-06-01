package com.example.project2.Admin.Service.Impl;

import com.example.project2.Admin.Entity.ClassroomEntity;
import com.example.project2.Admin.Entity.CourseEntity;
import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Model.Request.TimetableReq;
import com.example.project2.Admin.Model.Response.TimetableRes;
import com.example.project2.Admin.Repository.ClassroomRepository;
import com.example.project2.Admin.Repository.CourseRepository;
import com.example.project2.Admin.Repository.TimeTableRepository;
import com.example.project2.Admin.Service.TimetableServiceIntf;
import com.example.project2.Commom.Exception.IdNotFoundException;
import com.example.project2.Commom.Exception.InternalServerError;
import com.example.project2.Commom.Exception.TimetableIsExist;
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import com.example.project2.Student.Entity.StudentEntity;
import com.example.project2.Student.Repository.StudentRepository;
import com.example.project2.Teacher.Entity.TeacherEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TimetableServiceImpl implements TimetableServiceIntf {
    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    ClassroomRepository classroomRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<TimetableRes> convertToLístTimetableRes(List<TimetableEntity> list) {
        List<TimetableRes> listTimetableRes = list.stream().map(timetable -> {
            TimetableRes timetableRes = new TimetableRes();
            timetableRes.setId(timetable.getId());
            timetableRes.setClassName(timetable.getClassroomEntity().getClassroomName());
            timetableRes.setCourseName(timetable.getCourseEntity().getCourseName());
            String teacherName = personalRepository.findByAccountId(timetable.getTeacherEntity().getAccountId()).get().getLastName();
            timetableRes.setTeacherName(teacherName);
            timetableRes.setCourseId(timetable.getCourseId());
            timetableRes.setClassroomId(timetable.getClassroomId());
            timetableRes.setTeacherId(timetable.getTeacherId());
            timetableRes.setDayOfWeek(timetable.getDayOfWeek());
            timetableRes.setShift(timetable.getShift());
            return timetableRes;
        }).collect(Collectors.toList());

        return listTimetableRes;
    }

    public TimetableEntity convertToTimetableEntity(TimetableReq timetableReq) {
        TimetableEntity timetableEntity = new TimetableEntity();
        timetableEntity.setClassroomId(timetableReq.getClassroomId());
        timetableEntity.setTeacherId(timetableReq.getTeacherId());
        timetableEntity.setCourseId(timetableReq.getCourseId());
        timetableEntity.setShift(timetableReq.getShift());
        timetableEntity.setDayOfWeek(timetableReq.getDayOfWeek());
        return timetableEntity;
    }

    @Override
    public Map<String, Boolean> deleteById(Integer id) throws IdNotFoundException {
        Map<String, Boolean> result = new HashMap<>();
        if (timeTableRepository.findById(id).isPresent()) {
            timeTableRepository.deleteById(id);
            result.put("message", true);
            return result;
        }
        throw new IdNotFoundException(id);
    }


    @Override
    public List<TimetableRes> findAll() {
        List<TimetableEntity> list = timeTableRepository.findAll();
        List<TimetableRes> listTimetableRes = convertToLístTimetableRes(list);

        return listTimetableRes;
    }

    @Override
    public Map<String, String> insertTimetable(Integer id, TimetableReq timetableReq) throws TimetableIsExist {
//        ModelMapper modelMapper = new ModelMapper();
//        TimetableEntity timetableEntity = modelMapper.map(timetableReq, TimetableEntity.class);
        Map<String, String> result = new HashMap<>();

        List<TimetableEntity> listTeacherId = timeTableRepository.findByTeacherId(timetableReq.getTeacherId())
                .stream()
                .filter(timetableEntity -> {
                    return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                            && timetableReq.getShift() == timetableEntity.getShift();
                }).collect(Collectors.toList());
        List<TimetableEntity> listTimetableFromClassId = timeTableRepository.findByClassroomId(timetableReq.getClassroomId())
                .stream()
                .filter(timetableEntity -> {
                    return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                            && timetableReq.getShift() == timetableEntity.getShift();
                }).collect(Collectors.toList());
        if (listTeacherId.isEmpty() && listTimetableFromClassId.isEmpty()) {
            //convert
            TimetableEntity timetableEntity = convertToTimetableEntity(timetableReq);
            timetableEntity.setId(id);
            timeTableRepository.save(timetableEntity);
            result.put("message", "success");
            return result;
        }
        throw new TimetableIsExist("Timetable is exist");

    }

    @Override
    public List<TimetableEntity> getTeacherFromId(Integer teacherId) {
        return timeTableRepository.findByTeacherId(teacherId);
    }

    @Override
    public List<TimetableRes> searchTimetableDetailsByPage(String className, String teacherName, String courseName, Pageable pageable) {
        Page<TimetableEntity> timetableEntityPage = timeTableRepository.findByAllDetail(className, teacherName, courseName, pageable);
        List<TimetableRes> timetableResList = convertToLístTimetableRes(timetableEntityPage.toList());
        return timetableResList;
    }

    @Override
    public List<TimetableRes> searchTimetableByPage(String value, Pageable pageable) {
        Page<TimetableEntity> page = timeTableRepository.findByClassroomName(value, pageable);
        List<TimetableRes> lístTimetableRes = convertToLístTimetableRes(page.toList());
        return lístTimetableRes;
    }

    @Override
    public List<TimetableRes> getAllByPage(Pageable pageable) {
        Page<TimetableEntity> page = timeTableRepository.findAllByPage(pageable);
        List<TimetableEntity> timetableEntityList = page.toList();
        return convertToLístTimetableRes(timetableEntityList);

    }

    @Override
    public Map<String, String> addNew(TimetableReq timetableReq) throws TimetableIsExist {
        Map<String, String> result = new HashMap<>();

        List<TimetableEntity> listTeacherId = timeTableRepository.findByTeacherId(timetableReq.getTeacherId())
                .stream()
                .filter(timetableEntity -> {
                    return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                            && timetableReq.getShift() == timetableEntity.getShift();
                }).collect(Collectors.toList());
        List<TimetableEntity> listTimetableFromClassId = timeTableRepository.findByClassroomId(timetableReq.getClassroomId())
                .stream()
                .filter(timetableEntity -> {
                    return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                            && timetableReq.getShift() == timetableEntity.getShift();
                }).collect(Collectors.toList());
        if (listTeacherId.isEmpty() && listTimetableFromClassId.isEmpty()) {
            //convert
            TimetableEntity timetableEntity = convertToTimetableEntity(timetableReq);
            timeTableRepository.save(timetableEntity);
            result.put("message", "success");
            return result;
        }
        throw new TimetableIsExist("Timetable is exist");

    }

    @Override
    public List<TimetableRes> getTimetableFromStudentId(Integer accountId) throws IdNotFoundException {
        Integer studentId = studentRepository.findByAccountId(accountId).getId();
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        if (studentEntity.isPresent()) {
            List<TimetableEntity> timetableEntityList = timeTableRepository.findByClassroomId(studentEntity.get().getClassroomId());
            List<TimetableRes> timetableResList = convertToLístTimetableRes(timetableEntityList);
            return timetableResList;
        } else {
            throw new IdNotFoundException(studentId);
        }
    }

    public List<TimetableEntity> test(Integer id, TimetableReq timetableReq) throws InternalServerError {
//        ModelMapper modelMapper = new ModelMapper();
//        TimetableEntity timetableEntity = modelMapper.map(timetableReq, TimetableEntity.class);
        List<TimetableEntity> listTeacherId = timeTableRepository.findByTeacherId(timetableReq.getTeacherId()).stream()
                .filter(timetableEntity -> {
                    return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                            && timetableReq.getShift() == timetableEntity.getShift();
                }).collect(Collectors.toList());
        return listTeacherId;
    }
}
