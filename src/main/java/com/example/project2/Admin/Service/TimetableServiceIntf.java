package com.example.project2.Admin.Service;

import com.example.project2.Admin.Entity.TimetableEntity;
import com.example.project2.Admin.Model.Request.TimetableReq;
import com.example.project2.Admin.Model.Response.TimetableRes;
import com.example.project2.Teacher.Entity.TeacherEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TimetableServiceIntf {
    public List<TimetableRes> findAll();

    public TimetableEntity insertTimetable(Integer id, TimetableReq timetableReq);

    public Map<String, String> addNew(TimetableReq timetableReq);

    public List<TimetableEntity> getTeacherFromId(Integer teacherId);

    public List<TimetableEntity> getTimetableFromStudentId(Integer studentId);

    public List<TimetableRes> getAllByPage(Pageable pageable);
}
