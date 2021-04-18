package com.example.project2.Admin.Service.Impl;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Model.Response.TimetableRes;
import com.example.project2.Admin.Repository.TimeTableRepository;
import com.example.project2.Admin.Service.TimetableServiceIntf;
import com.example.project2.PersonalInfor.Entity.PersonalEntity;
import com.example.project2.PersonalInfor.Repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimetableServiceImpl implements TimetableServiceIntf {
    @Autowired
    TimeTableRepository timeTableRepository;

    @Autowired
    PersonalRepository personalRepository;

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
}
