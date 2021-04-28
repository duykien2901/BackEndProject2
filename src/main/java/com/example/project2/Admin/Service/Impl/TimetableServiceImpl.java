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
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import com.example.project2.Teacher.Entity.TeacherEntity;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    @Override
    public List<TimetableRes> findAll() {
        List<TimetableEntity> list = timeTableRepository.findAll();
        List<TimetableRes> listTimetableRes = list.stream().map(timetable -> {
            TimetableRes timetableRes = new TimetableRes();
            timetableRes.setId(timetable.getId());
            timetableRes.setClassName(timetable.getClassroomEntity().getClassroomName());
            timetableRes.setCourseName(timetable.getCourseEntity().getCourseName());
            String teacherName = personalRepository.findByAccountId(timetable.getTeacherEntity().getAccountId()).get().getLastName();
            timetableRes.setTeacherName(teacherName);
            timetableRes.setDayOfWeek(timetable.getDayOfWeek());
            timetableRes.setShift(timetable.getShift());
            return timetableRes;
        }).collect(Collectors.toList());

        return listTimetableRes;
    }

    @Override
    public TimetableEntity insertTimetable(Integer id, TimetableReq timetableReq) throws InternalServerError{
//        ModelMapper modelMapper = new ModelMapper();
//        TimetableEntity timetableEntity = modelMapper.map(timetableReq, TimetableEntity.class);
        try {
            TimetableEntity timetableEntity = new TimetableEntity();
            timetableEntity.setId(id);
            timetableEntity.setClassroomId(timetableReq.getClassroomId());
            timetableEntity.setTeacherId(timetableReq.getTeacherId());
            timetableEntity.setCourseId(timetableReq.getCourseId());
            timetableEntity.setShift(timetableReq.getShift());
            timetableEntity.setDayOfWeek(timetableReq.getDayOfWeek());
            timeTableRepository.save(timetableEntity);
            return timetableEntity;
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new InternalServerError();
        }
    }

    @Override
    public List<TimetableEntity> getTeacherFromId(Integer teacherId) {
        return timeTableRepository.findByTeacherId(teacherId);
    }

    @Override
    public Map<String, String> addNew(TimetableReq timetableReq) throws InternalServerError{
        Map<String, String> result = new HashMap<>();
        try {
            List<TimetableEntity> listTeacherId = timeTableRepository.findByTeacherId(timetableReq.getTeacherId()).stream()
                    .filter(timetableEntity -> {
                        return timetableReq.getDayOfWeek() == timetableEntity.getDayOfWeek()
                                && timetableReq.getShift() == timetableEntity.getShift();
                    }).collect(Collectors.toList());
            if(!listTeacherId.isEmpty()) {
                result.put("message", "The timetable is on the same schedule");
                return result;
            } else {
                TimetableEntity timetableEntity = new TimetableEntity();
                timetableEntity.setClassroomId(timetableReq.getClassroomId());
                timetableEntity.setTeacherId(timetableReq.getTeacherId());
                timetableEntity.setCourseId(timetableReq.getCourseId());
                timetableEntity.setShift(timetableReq.getShift());
                timetableEntity.setDayOfWeek(timetableReq.getDayOfWeek());
                timeTableRepository.save(timetableEntity);
                result.put("message", "success");
                return result;
            }

        }catch (Exception e){
            result.put("message", "add fail");
            return result;
        }
    }

    public List<TimetableEntity> test(Integer id, TimetableReq timetableReq) throws InternalServerError{
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
